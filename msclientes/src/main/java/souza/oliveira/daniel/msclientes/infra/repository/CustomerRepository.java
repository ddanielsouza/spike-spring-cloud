package souza.oliveira.daniel.msclientes.infra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import souza.oliveira.daniel.msclientes.domain.entity.Customer;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByCpf(String cpf);
    boolean existsByCpf(String cpf);

}
