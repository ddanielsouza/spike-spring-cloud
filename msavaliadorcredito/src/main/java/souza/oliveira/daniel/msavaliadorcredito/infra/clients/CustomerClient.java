package souza.oliveira.daniel.msavaliadorcredito.infra.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import souza.oliveira.daniel.msavaliadorcredito.domain.model.CustomerDetails;

@FeignClient(value = "msclientes", path = "/customers")
public interface CustomerClient {
    @GetMapping(path = "/status")
    public String status();

    @GetMapping
    public ResponseEntity<CustomerDetails> getCustomerData(@RequestParam("cpf") String cpf);
}
