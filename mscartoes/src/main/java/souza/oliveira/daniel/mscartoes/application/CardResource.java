package souza.oliveira.daniel.mscartoes.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import souza.oliveira.daniel.mscartoes.application.dto.CardSaveRequestDTO;
import souza.oliveira.daniel.mscartoes.application.mapper.CardMapper;
import souza.oliveira.daniel.mscartoes.domain.Card;
import souza.oliveira.daniel.mscartoes.services.CardService;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/cards")
public class CardResource {

    private final CardService cardService;
    private final CardMapper cardMapper;

    @Autowired
    public CardResource(CardService cardService, CardMapper cardMapper) {
        this.cardService = cardService;
        this.cardMapper = cardMapper;
    }

    @GetMapping("/status")
    public String status (){
        return "OK";
    }


    @PostMapping
    public ResponseEntity save(@RequestBody CardSaveRequestDTO dto){
        final Card card = this.cardMapper.toEntity(dto);
        this.cardService.save(card);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity getCardsByIncomeLessThanEqual(
            @RequestParam("income") BigDecimal income){
        final List<Card> cards = this.cardService
                .getCardsByIncomeLessThanEqual(income);
        
        final var dtos = this.cardMapper.toDTO(cards);

        return ResponseEntity.ok(dtos);
    }
}
