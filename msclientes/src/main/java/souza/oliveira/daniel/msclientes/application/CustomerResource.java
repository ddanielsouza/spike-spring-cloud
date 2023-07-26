package souza.oliveira.daniel.msclientes.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import souza.oliveira.daniel.msclientes.application.dto.CustomerDetailsResponseDTO;
import souza.oliveira.daniel.msclientes.application.dto.CustomerSaveRequestDTO;
import souza.oliveira.daniel.msclientes.application.mapper.CustomerMapper;
import souza.oliveira.daniel.msclientes.domain.entity.Customer;
import souza.oliveira.daniel.msclientes.service.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerResource {
    private final CustomerMapper customerMapper;
    private final CustomerService customerService;

    @Autowired
    public CustomerResource(CustomerMapper customerMapper, CustomerService customerService) {
        this.customerMapper = customerMapper;
        this.customerService = customerService;
    }

    @GetMapping(path = "/status")
    public String status(){
        return "Service is running";
    }

    @PostMapping
    public ResponseEntity save(@RequestBody CustomerSaveRequestDTO dto){
        final var customer = this.customerMapper.customerSaveRequestToCustomer(dto);
        this.customerService.save(customer);
        final var headerLocation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .query("cpf={cpf}")
                .buildAndExpand(customer.getCpf())
                .toUri();

        return ResponseEntity.created(headerLocation).build();
    }

    @GetMapping
    public ResponseEntity<CustomerDetailsResponseDTO> getCustomerData(@RequestParam("cpf") String cpf){
        var customer = this.customerService.getByCPF(cpf);

        if(customer.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok( this.customerMapper.toDTO(customer.get()));
    }
}
