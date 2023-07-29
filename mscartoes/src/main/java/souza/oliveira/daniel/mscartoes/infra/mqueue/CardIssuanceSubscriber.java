package souza.oliveira.daniel.mscartoes.infra.mqueue;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class CardIssuanceSubscriber {
    @RabbitListener(queues = "${mq.queues.card-issuance}")
    public void receiveCardIssuance(@Payload String payload){
        System.out.println(payload);
    }
}
