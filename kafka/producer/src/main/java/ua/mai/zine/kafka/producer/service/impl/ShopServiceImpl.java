package ua.mai.zine.kafka.producer.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import ua.mai.zine.kafka.dto.ShopCreateDto;
import ua.mai.zine.kafka.event.ShopCreateEvent;
import ua.mai.zine.kafka.producer.service.ShopService;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Service
public class ShopServiceImpl implements ShopService {

    public static final Logger log = LoggerFactory.getLogger(ShopServiceImpl.class);

    private final KafkaTemplate<String, ShopCreateEvent> kafkaTemplate;
    private final String createTopic;

    public ShopServiceImpl(@Autowired KafkaTemplate<String, ShopCreateEvent> kafkaTemplate,
                           @Value("${zine.kafka.product-create-topic.name}") String createTopic) {
        this.kafkaTemplate = kafkaTemplate;
        this.createTopic = createTopic;
    }

    @Override
    public String createShop(ShopCreateDto dto) throws ExecutionException, InterruptedException {

        // TODO save to DB

        String shopId = UUID.randomUUID().toString();
        ShopCreateEvent shopCreateEvent = ShopCreateEvent.create(shopId, dto);

        SendResult<String, ShopCreateEvent> result =
                kafkaTemplate.send(createTopic, shopId, shopCreateEvent)
                             .get();

        log.info("Shop created: shoptId={} title={}", shopId, dto.title());
        log.info("Topic: {}", result.getRecordMetadata().topic());
        log.info("Partition: {}", result.getRecordMetadata().partition());
        log.info("Offset: {}", result.getRecordMetadata().offset());

        return shopId;
    }

}
