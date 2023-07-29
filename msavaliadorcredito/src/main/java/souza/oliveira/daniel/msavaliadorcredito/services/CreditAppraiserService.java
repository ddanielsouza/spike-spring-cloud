package souza.oliveira.daniel.msavaliadorcredito.services;

import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import souza.oliveira.daniel.msavaliadorcredito.application.exception.ApplicationException;
import souza.oliveira.daniel.msavaliadorcredito.domain.model.*;
import souza.oliveira.daniel.msavaliadorcredito.infra.clients.CardClient;
import souza.oliveira.daniel.msavaliadorcredito.infra.clients.CustomerClient;
import souza.oliveira.daniel.msavaliadorcredito.infra.mqueue.CardIssuancePublisher;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CreditAppraiserService {
    private final static int SCORE_TO_DECIMAL_PERCENT = 10;
    private final static int DECIMAL_TO_PERCENT = 100;
    private final CustomerClient customerClient;
    private final CardClient cardClient;
    private final CardIssuancePublisher cardIssuancePublisher;

    @Autowired
    public CreditAppraiserService(CustomerClient customerClient,
                                  CardClient cardClient, CardIssuancePublisher cardIssuancePublisher) {
        this.customerClient = customerClient;
        this.cardClient = cardClient;
        this.cardIssuancePublisher = cardIssuancePublisher;
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
     * @param data
     * @return Fictitious protocol for didactic purposes
     */
    public ProtocolCardIssuance sendCardIssuance(RequestCardIssuance data){
        try{
            this.cardIssuancePublisher.sendCardIssuanceToQueue(data);
            var protocol = UUID.randomUUID().toString();

            return new ProtocolCardIssuance(protocol);
        }catch (Exception e ){
            throw new ApplicationException("Failed to send card issuance", e, HttpStatus.INTERNAL_SERVER_ERROR);
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
