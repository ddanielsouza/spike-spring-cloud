package souza.oliveira.daniel.msavaliadorcredito.services;

import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import souza.oliveira.daniel.msavaliadorcredito.application.exception.ApplicationException;
import souza.oliveira.daniel.msavaliadorcredito.domain.model.ApprovedCard;
import souza.oliveira.daniel.msavaliadorcredito.domain.model.CustomerSituation;
import souza.oliveira.daniel.msavaliadorcredito.domain.model.ResultAssessCustomerCredit;
import souza.oliveira.daniel.msavaliadorcredito.infra.clients.CardClient;
import souza.oliveira.daniel.msavaliadorcredito.infra.clients.CustomerClient;

import java.math.BigDecimal;
import java.util.stream.Collectors;

@Service
public class CreditAppraiserService {
    private final static int SCORE_TO_DECIMAL_PERCENT = 10;
    private final static int DECIMAL_TO_PERCENT = 100;
    private final CustomerClient customerClient;
    private final CardClient cardClient;

    @Autowired
    public CreditAppraiserService(CustomerClient customerClient,
                                  CardClient cardClient) {
        this.customerClient = customerClient;
        this.cardClient = cardClient;
    }

    public CustomerSituation getCustomerSituation(String cpf) {
        try {
            final var customerData = this.customerClient.getCustomerData(cpf);
            final var cards = this.cardClient.getCardsByCustomerCPF(cpf);

            return CustomerSituation
                    .builder()
                    .customer(customerData.getBody())
                    .cards(cards.getBody())
                    .build();
        } catch (FeignException.FeignClientException ex) {
            throw new ApplicationException("", ex, ex.status());
        }
    }

    public ResultAssessCustomerCredit assessCustomerCredit(String cpf, BigDecimal income) {
        try {
            final var customer = this.customerClient.getCustomerData(cpf).getBody();
            final var cards = this.cardClient.getCardsByIncomeLessThanEqual(income).getBody();
            final var score =  this.scoreCustomer(cpf);
            final var factor = new BigDecimal(this.calculationFactor(score));

            final var approvedCards = cards.stream().map(card -> ApprovedCard
                        .builder()
                        .approvedLimit(card.getBasicLimit().multiply(factor))
                        .brand(card.getBrand())
                        .name(card.getName())
                        .build())
                    .collect(Collectors.toList());

            return ResultAssessCustomerCredit.builder().cards(approvedCards).build();
        } catch (FeignException.FeignClientException ex) {
            throw new ApplicationException("", ex, ex.status());
        }
    }

    /**
     * @param cpf
     * @implNote Simulate the return of a customer score request
     * @return score of client
     */
    private int scoreCustomer(String cpf){
        return (int) ((Math.random() * (1000 - 0)) + 1000);
    }

    /**
     * @param score
     * @implNote Dummy rule to generate a conversion factor to calculate customer limit
     * @return Calculation Factor of customer income
     */
    private int calculationFactor(int score){
        return score / SCORE_TO_DECIMAL_PERCENT  / DECIMAL_TO_PERCENT + 1;
    }
}
