package souza.oliveira.daniel.mscartoes.infra.mqueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import souza.oliveira.daniel.mscartoes.application.dto.RequestCardIssuanceDTO;
import souza.oliveira.daniel.mscartoes.application.mapper.CardMapper;
import souza.oliveira.daniel.mscartoes.infra.repository.CardRepository;
import souza.oliveira.daniel.mscartoes.infra.utils.JSONUtils;
import souza.oliveira.daniel.mscartoes.services.CustomerCardService;

@Component
public class CardIssuanceSubscriber {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final CardRepository cardRepository;
    private final CardMapper cardMapper;
    private final CustomerCardService customerCardService;

    @Autowired
    public CardIssuanceSubscriber(CardRepository cardRepository,
                                  CardMapper cardMapper,
                                  CustomerCardService customerCardService) {

        this.cardRepository = cardRepository;
        this.cardMapper = cardMapper;
        this.customerCardService = customerCardService;
    }

    @RabbitListener(queues = "${mq.queues.card-issuance}")
    public void receiveCardIssuance(@Payload String payload) {
        try{
            final var data =
                    JSONUtils.toObject(payload, RequestCardIssuanceDTO.class);

            final var card = this.cardRepository.findById(data.getCardId())
                    .orElseThrow();

            final var customerCard = this.cardMapper.cardWithCardIssuanceToCustomerCard(card, data);

            this.customerCardService.save(customerCard);
        }catch (Exception e ) {
            log.error("Failed in receive card issuance", e);
        }
    }
}
