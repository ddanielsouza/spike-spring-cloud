package souza.oliveira.daniel.mscartoes.application.dto;

import lombok.Data;
import souza.oliveira.daniel.mscartoes.domain.CardBrand;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;

@Data
public class CardResponseDTO {
    private Long id;
    private String name;
    private CardBrand brand;
    private BigDecimal income;
    private BigDecimal basicLimit;
}
