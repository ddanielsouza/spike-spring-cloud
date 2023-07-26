package souza.oliveira.daniel.msavaliadorcredito.infra.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import souza.oliveira.daniel.msavaliadorcredito.domain.model.CardDetails;
import souza.oliveira.daniel.msavaliadorcredito.domain.model.CustomerCard;

import java.math.BigDecimal;
import java.util.List;

@FeignClient(value = "mscartoes", path = "cards")
public interface CardClient {
    @GetMapping("/customers")
    ResponseEntity<List<CustomerCard>> getCardsByCustomerCPF(@RequestParam("cpf") String cpf);

    @GetMapping
    public ResponseEntity<List<CardDetails>> getCardsByIncomeLessThanEqual(@RequestParam("income") BigDecimal income);
}
