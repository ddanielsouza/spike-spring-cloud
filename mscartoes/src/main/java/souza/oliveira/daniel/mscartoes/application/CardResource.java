package souza.oliveira.daniel.mscartoes.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import souza.oliveira.daniel.mscartoes.application.dto.CardSaveRequestDTO;
import souza.oliveira.daniel.mscartoes.application.mapper.CardMapper;
import souza.oliveira.daniel.mscartoes.domain.entity.Card;
import souza.oliveira.daniel.mscartoes.domain.entity.CustomerCard;
import souza.oliveira.daniel.mscartoes.services.CardService;
import souza.oliveira.daniel.mscartoes.services.CustomerCardService;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/cards")
public class CardResource {

    private final CardService cardService;
    private final CardMapper cardMapper;
    private final CustomerCardService customerCardService;

    @Autowired
    public CardResource(CardService cardService,
                        CardMapper cardMapper,
                        CustomerCardService customerCardService) {
        this.cardService = cardService;
        this.cardMapper = cardMapper;
        this.customerCardService = customerCardService;
    }

    @GetMapping("/status")
    public String status (){
        return "OK";
    }


    @PostMapping
    public ResponseEntity save(@RequestBody CardSaveRequestDTO dto){
        final Card card = this.cardMapper.cardSaveRequestToEntity(dto);
        this.cardService.save(card);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity getCardsByIncomeLessThanEqual(
            @RequestParam("income") BigDecimal income){
        final List<Card> cards = this.cardService
                .getCardsByIncomeLessThanEqual(income);

        final var dtos = this.cardMapper.toCardResponse(cards);

        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/customers")
    public ResponseEntity getCardsByCustomerCPF(@RequestParam("cpf") String cpf){
        final List<CustomerCard> cards = this.customerCardService.getByCustomerCPF(cpf);
        final var dtos = this.cardMapper.toCardByCustomerResponse(cards);

        return ResponseEntity.ok(dtos);
    }
}
