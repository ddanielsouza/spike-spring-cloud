package souza.oliveira.daniel.msclientes.application;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import souza.oliveira.daniel.msclientes.application.dto.CustomerSaveRequestDTO;
import souza.oliveira.daniel.msclientes.application.mapper.CustomerMapper;
import souza.oliveira.daniel.msclientes.domain.Customer;
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
    public ResponseEntity<Customer> getCustomerData(@RequestParam("cpf") String cpf){
        var customer = this.customerService.getByCPF(cpf);

        if(customer.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(customer.get());
    }
}
