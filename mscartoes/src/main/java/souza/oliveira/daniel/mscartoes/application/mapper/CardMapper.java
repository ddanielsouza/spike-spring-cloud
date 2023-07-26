package souza.oliveira.daniel.mscartoes.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import souza.oliveira.daniel.mscartoes.application.dto.CardByCustomerResponseDTO;
import souza.oliveira.daniel.mscartoes.application.dto.CardResponseDTO;
import souza.oliveira.daniel.mscartoes.application.dto.CardSaveRequestDTO;
import souza.oliveira.daniel.mscartoes.domain.Card;
import souza.oliveira.daniel.mscartoes.domain.CustomerCard;

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

}
