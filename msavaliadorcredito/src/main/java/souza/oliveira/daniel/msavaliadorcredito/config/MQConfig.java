package souza.oliveira.daniel.msavaliadorcredito.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {
    @Value("${mq.queues.card-issuance}")
    private String cardIssuance;

    @Bean
    public Queue queueCardIssuance(){
        return new Queue(cardIssuance, true);
    }
}
