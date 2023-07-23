package souza.oliveira.daniel.msclientes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import souza.oliveira.daniel.msclientes.domain.Customer;
import souza.oliveira.daniel.msclientes.infra.repository.CustomerRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepository customerrepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerrepository = customerRepository;
    }

    @Transactional
    public Customer save(Customer customer){
        return this.customerrepository.save(customer);
    }

    public Optional<Customer> getByCPF(String cpf){
        return this.customerrepository.findByCpf(cpf);
    }
}
