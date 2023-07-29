package souza.oliveira.daniel.msavaliadorcredito.infra.mqueue;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import souza.oliveira.daniel.msavaliadorcredito.domain.model.RequestCardIssuance;
import souza.oliveira.daniel.msavaliadorcredito.infra.utils.JsonUtils;

@Component
public class CardIssuancePublisher {
    private final RabbitTemplate rabbitTemplate;
    private final Queue queueCardIssuance;

    @Autowired
    public CardIssuancePublisher(RabbitTemplate rabbitTemplate, Queue queueCardIssuance) {
        this.rabbitTemplate = rabbitTemplate;
        this.queueCardIssuance = queueCardIssuance;
    }

    public void sendCardIssuanceToQueue(RequestCardIssuance data) throws JsonProcessingException {
        final var json = JsonUtils.toJSON(data);
        this.rabbitTemplate.convertAndSend(queueCardIssuance.getName(), json);
    }
}
