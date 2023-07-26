package souza.oliveira.daniel.msavaliadorcredito.application;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/credit-appraiser")
public class CreditAppraiserResource {
    @GetMapping("/status")
    public String status(){
        return "OK";
    }
}
