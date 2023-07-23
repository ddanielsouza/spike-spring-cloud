package souza.oliveira.daniel.msclientes.application.dto;

import lombok.Data;

@Data
public class CustomerSaveRequestDTO {
    private String cpf;
    private String name;
    private Integer age;
}
