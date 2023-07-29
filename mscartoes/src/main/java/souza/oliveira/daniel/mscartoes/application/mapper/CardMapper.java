package souza.oliveira.daniel.mscartoes.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import souza.oliveira.daniel.mscartoes.application.dto.CardByCustomerResponseDTO;
import souza.oliveira.daniel.mscartoes.application.dto.CardResponseDTO;
import souza.oliveira.daniel.mscartoes.application.dto.CardSaveRequestDTO;
import souza.oliveira.daniel.mscartoes.application.dto.RequestCardIssuanceDTO;
import souza.oliveira.daniel.mscartoes.domain.entity.Card;
import souza.oliveira.daniel.mscartoes.domain.entity.CustomerCard;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface CardMapper {

    Card cardSaveRequestToEntity(CardSaveRequestDTO dto);
    CardResponseDTO toCardResponse(Card card);

    default List<CardResponseDTO> toCardResponse(List<Card> cards) {
        if(cards == null)
            return null;
        
        return cards.stream().map(this::toCardResponse).collect(Collectors.toList());
    }

    @Mapping(source = "card.name", target = "name")
    @Mapping(source = "card.brand", target = "brand")
    @Mapping(source = "limit", target = "limit")
    CardByCustomerResponseDTO toCardByCustomerResponse(CustomerCard card);

    default List<CardByCustomerResponseDTO> toCardByCustomerResponse(List<CustomerCard> cards) {
        if(cards == null)
            return null;

        return cards.stream().map(this::toCardByCustomerResponse).collect(Collectors.toList());
    }

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "card", target = "card")
    @Mapping(source = "dto.cpf", target = "cpf")
    @Mapping(source = "dto.limitReleased", target = "limit")
    CustomerCard cardWithCardIssuanceToCustomerCard(Card card, RequestCardIssuanceDTO dto);
}
