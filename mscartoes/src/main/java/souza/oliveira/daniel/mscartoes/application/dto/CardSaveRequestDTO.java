package souza.oliveira.daniel.mscartoes.application.dto;

import lombok.Data;
import souza.oliveira.daniel.mscartoes.domain.entity.CardBrand;

import java.math.BigDecimal;

@Data
public class CardSaveRequestDTO {
    private String name;
    private CardBrand brand;
    private BigDecimal income;
    private BigDecimal basicLimit;
}
