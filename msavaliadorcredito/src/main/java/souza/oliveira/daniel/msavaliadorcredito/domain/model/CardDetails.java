package souza.oliveira.daniel.msavaliadorcredito.domain.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CardDetails {
    private Long id;
    private String name;
    private String brand;
    private BigDecimal income;
    private BigDecimal basicLimit;
}
