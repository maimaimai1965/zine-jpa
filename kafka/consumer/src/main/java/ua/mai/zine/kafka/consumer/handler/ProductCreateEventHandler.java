package ua.mai.zine.kafka.consumer.handler;

import lombok.NonNull;
import lombok.extern.log4j.Log4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ua.mai.zine.kafka.consumer.exception.NonRetryableException;
import ua.mai.zine.kafka.consumer.exception.RetryableException;
import ua.mai.zine.kafka.event.ProductCreateEvent;

import java.util.HashMap;
import java.util.Map;

@Component
@KafkaListener(topics = "${zine.kafka.product-create-topic.name}")
public class ProductCreateEventHandler {

    public static final Logger log = LoggerFactory.getLogger(ProductCreateEventHandler.class);


    @KafkaHandler
    public void handle(@NonNull ProductCreateEvent productCreateEvent) {

        if (productCreateEvent.title().equals("RetryableException"))
            throw new RetryableException("RetryableException");
        else
        if (productCreateEvent.title().equals("NonRetryableException"))
            throw new NonRetryableException("NonRetryableException move into DLQ");

        Map<Character, Integer> map = new HashMap<>();

        log.info("Received ProductCreateEvent: {} {}", productCreateEvent.productId(), productCreateEvent.title());
    }

}
