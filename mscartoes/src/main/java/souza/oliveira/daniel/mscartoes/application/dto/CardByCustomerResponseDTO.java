package souza.oliveira.daniel.mscartoes.application.dto;

import lombok.Data;
import souza.oliveira.daniel.mscartoes.domain.CardBrand;

import java.math.BigDecimal;

@Data
public class CardByCustomerResponseDTO {
    private String name;
    private CardBrand brand;
    private BigDecimal limit;
}