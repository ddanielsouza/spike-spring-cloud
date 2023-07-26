package souza.oliveira.daniel.msclientes.application.dto;

import lombok.Data;

@Data
public class CustomerDetailsResponseDTO {
    private Long id;
    private String cpf;
    private String name;
    private Integer age;
}
