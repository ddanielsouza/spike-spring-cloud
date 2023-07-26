package souza.oliveira.daniel.mscartoes.infra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import souza.oliveira.daniel.mscartoes.domain.entity.CustomerCard;

import java.util.List;

public interface CustomerCardRepository extends JpaRepository<CustomerCard, Long> {
    List<CustomerCard> findByCpf(String cpf);
}
