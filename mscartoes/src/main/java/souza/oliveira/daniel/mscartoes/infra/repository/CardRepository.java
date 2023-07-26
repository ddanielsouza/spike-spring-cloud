package souza.oliveira.daniel.mscartoes.infra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import souza.oliveira.daniel.mscartoes.domain.entity.Card;

import java.math.BigDecimal;
import java.util.List;

public interface CardRepository extends JpaRepository<Card, Long> {
    List<Card> findByIncomeLessThanEqual(BigDecimal income);
}
