package souza.oliveira.daniel.mscartoes.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import souza.oliveira.daniel.mscartoes.domain.entity.Card;
import souza.oliveira.daniel.mscartoes.infra.repository.CardRepository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
public class CardService {
    private final CardRepository cardRepository;

    @Autowired
    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Transactional
    public Card save(Card card){
        return this.cardRepository.save(card);
    }

    public List<Card> getCardsByIncomeLessThanEqual(Long income){
        return this.getCardsByIncomeLessThanEqual(BigDecimal.valueOf(income));
    }

    public List<Card> getCardsByIncomeLessThanEqual(BigDecimal income){
        return this.cardRepository.findByIncomeLessThanEqual(income);
    }
}
