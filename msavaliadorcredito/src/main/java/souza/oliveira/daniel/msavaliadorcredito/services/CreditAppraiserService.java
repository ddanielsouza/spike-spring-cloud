package souza.oliveira.daniel.msavaliadorcredito.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import souza.oliveira.daniel.msavaliadorcredito.domain.model.CustomerDetails;
import souza.oliveira.daniel.msavaliadorcredito.domain.model.CustomerSituation;
import souza.oliveira.daniel.msavaliadorcredito.infra.clients.CustomerClient;

@Service
public class CreditAppraiserService {

    private final CustomerClient customerClient;

    @Autowired
    public CreditAppraiserService(CustomerClient customerClient) {
        this.customerClient = customerClient;
    }

    public CustomerSituation getCustomerSituation(String cpf){
        ResponseEntity<CustomerDetails> customerData = this.customerClient.getCustomerData(cpf);

        return CustomerSituation
                .builder()
                .customer(customerData.getBody())
                .build();
    }
}
