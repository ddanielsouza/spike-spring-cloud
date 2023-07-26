package souza.oliveira.daniel.mscartoes.application.mapper;

import org.mapstruct.Mapper;
import souza.oliveira.daniel.mscartoes.application.dto.CardResponseDTO;
import souza.oliveira.daniel.mscartoes.application.dto.CardSaveRequestDTO;
import souza.oliveira.daniel.mscartoes.domain.Card;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface CardMapper {

    Card toEntity(CardSaveRequestDTO dto);
    CardResponseDTO toDTO(Card card);

    default List<CardResponseDTO> toDTO(List<Card> cards) {
        if(cards == null)
            return null;

        return cards.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
