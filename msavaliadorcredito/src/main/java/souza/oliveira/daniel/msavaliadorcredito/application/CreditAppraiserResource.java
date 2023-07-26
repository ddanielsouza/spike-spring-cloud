package souza.oliveira.daniel.msavaliadorcredito.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import souza.oliveira.daniel.msavaliadorcredito.domain.model.CustomerSituation;
import souza.oliveira.daniel.msavaliadorcredito.domain.model.EvaluateCustomer;
import souza.oliveira.daniel.msavaliadorcredito.domain.model.ResultEvaluateCustomer;
import souza.oliveira.daniel.msavaliadorcredito.services.CreditAppraiserService;

@RestController
@RequestMapping("/credit-appraiser")
public class CreditAppraiserResource {

    private final CreditAppraiserService creditAppraiserService;

    @Autowired
    public CreditAppraiserResource(CreditAppraiserService creditAppraiserService) {
        this.creditAppraiserService = creditAppraiserService;
    }

    @GetMapping("/status")
    public String status() {
        return "OK";
    }

    @GetMapping("customer-situation")
    public ResponseEntity getCustomerSituation(@RequestParam("cpf") String cpf) {
        CustomerSituation customerSituation = this.creditAppraiserService.getCustomerSituation(cpf);
        return ResponseEntity.ok(customerSituation);
    }

    @PostMapping
    public ResponseEntity<ResultEvaluateCustomer> evaluateCustomer(@RequestBody EvaluateCustomer evaluateCustomer) {

        ResultEvaluateCustomer resultEvaluateCustomer = this.creditAppraiserService
                .evaluateCustomerCards(evaluateCustomer.getCpf(), evaluateCustomer.getIncome());

        return ResponseEntity.ok(resultEvaluateCustomer);
    }
}
