package ua.mai.zine.kafka.consumer.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ua.mai.zine.kafka.event.ProductCreateEvent;
import ua.mai.zine.kafka.event.ShopCreateEvent;

import java.util.HashMap;
import java.util.Map;

@Component
@KafkaListener(topics = "${zine.kafka.shop-create-topic.name}")
public class ShopCreateEventHandler {

    public static final Logger log = LoggerFactory.getLogger(ShopCreateEventHandler.class);


    @KafkaHandler
    public void handle(ShopCreateEvent shopCreateEvent) {
        Map<Character, Integer> map = new HashMap<>();

        log.info("Received ShopCreateEvent: {} {}", shopCreateEvent.shopId(), shopCreateEvent.title());
    }

}
