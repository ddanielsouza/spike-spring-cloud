package souza.oliveira.daniel.mscartoes.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestCardIssuanceDTO {
    private long cardId;
    private String cpf;
    private String address;
    private BigDecimal limitReleased;
}
