package souza.oliveira.daniel.msavaliadorcredito.domain.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AssessCustomerCredit {
    private String cpf;
    private BigDecimal income;
}
