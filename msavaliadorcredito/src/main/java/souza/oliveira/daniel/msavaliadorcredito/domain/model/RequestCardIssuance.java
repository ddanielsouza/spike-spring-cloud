package souza.oliveira.daniel.msavaliadorcredito.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestCardIssuance {
    private long cardId;
    private String cpf;
    private String address;
    private BigDecimal limitReleased;
}
