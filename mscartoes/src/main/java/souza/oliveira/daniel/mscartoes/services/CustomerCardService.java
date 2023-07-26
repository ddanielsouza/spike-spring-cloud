package souza.oliveira.daniel.mscartoes.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import souza.oliveira.daniel.mscartoes.domain.Card;
import souza.oliveira.daniel.mscartoes.domain.CustomerCard;
import souza.oliveira.daniel.mscartoes.infra.repository.CardRepository;
import souza.oliveira.daniel.mscartoes.infra.repository.CustomerCardRepository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
public class CustomerCardService {
    private final CustomerCardRepository customerCardRepository;

    @Autowired
    public CustomerCardService(CustomerCardRepository customerCardRepository) {
        this.customerCardRepository = customerCardRepository;
    }

    public List<CustomerCard> getByCustomerCPF(String cpf){
        return this.customerCardRepository.findByCpf(cpf);
    }
}
