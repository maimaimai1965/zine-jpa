package ua.mai.zine.kafka.consumer.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ua.mai.zine.kafka.event.ProductCreateEvent;

@Component
@KafkaListener(topics = "product-create-topic")
public class ProductCreateEventHandler {

    public static final Logger log = LoggerFactory.getLogger(ProductCreateEventHandler.class);


    @KafkaHandler
    public void handle(ProductCreateEvent productCreateEvent) {
        log.info("Received: {} {}", productCreateEvent.getProductId(), productCreateEvent.getTitle());
    }

}
