package souza.oliveira.daniel.msclientes.application.mapper;

import org.mapstruct.Mapper;
import souza.oliveira.daniel.msclientes.application.dto.CustomerSaveRequestDTO;
import souza.oliveira.daniel.msclientes.domain.Customer;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    Customer customerSaveRequestToCustomer(CustomerSaveRequestDTO dto);
}
