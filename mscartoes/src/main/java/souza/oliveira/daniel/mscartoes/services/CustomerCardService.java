package souza.oliveira.daniel.mscartoes.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import souza.oliveira.daniel.mscartoes.domain.entity.CustomerCard;
import souza.oliveira.daniel.mscartoes.infra.repository.CustomerCardRepository;

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
