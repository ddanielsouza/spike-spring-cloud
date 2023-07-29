package souza.oliveira.daniel.msavaliadorcredito.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import souza.oliveira.daniel.msavaliadorcredito.domain.model.CustomerSituation;
import souza.oliveira.daniel.msavaliadorcredito.domain.model.AssessCustomerCredit;
import souza.oliveira.daniel.msavaliadorcredito.domain.model.ResultAssessCustomerCredit;
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
    public ResponseEntity<ResultAssessCustomerCredit> assessCustomerCredit(@RequestBody AssessCustomerCredit assessCustomer) {

        ResultAssessCustomerCredit resultAssessCustomerCredit = this.creditAppraiserService
                .assessCustomerCredit(assessCustomer.getCpf(), assessCustomer.getIncome());

        return ResponseEntity.ok(resultAssessCustomerCredit);
    }
}
