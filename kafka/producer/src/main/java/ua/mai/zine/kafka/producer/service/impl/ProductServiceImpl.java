package ua.mai.zine.kafka.producer.service.impl;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import ua.mai.zine.kafka.dto.ProductCreateDto;
import ua.mai.zine.kafka.event.ProductCreateEvent;
import ua.mai.zine.kafka.producer.service.ProductService;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Service
public class ProductServiceImpl implements ProductService {

    public static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final KafkaTemplate<String, ProductCreateEvent> kafkaTemplate;
    private final String productCreateTopic;

    public ProductServiceImpl(@Autowired KafkaTemplate<String, ProductCreateEvent> kafkaTemplate,
                              @Value("${zine.kafka.product-create-topic.name}") String productCreateTopic) {
        this.kafkaTemplate = kafkaTemplate;
        this.productCreateTopic = productCreateTopic;
    }

    @Override
    public String createProduct(ProductCreateDto dto) throws ExecutionException, InterruptedException {

        // TODO save to DB

        String productId = UUID.randomUUID().toString();
        ProductCreateEvent productCreateEvent = ProductCreateEvent.create(productId, dto);

        ProducerRecord<String, ProductCreateEvent> record = new ProducerRecord<>(
                productCreateTopic,
                productId,
                productCreateEvent);
        record.headers().add("messageId", UUID.randomUUID().toString().getBytes());

        SendResult<String,ProductCreateEvent> result =
                kafkaTemplate.send(record)
                             .get();

        log.info("Product created: productId={} title={}", productId, dto.title());
        log.info("                 Topic:     {}", result.getRecordMetadata().topic());
        log.info("                 Partition: {}", result.getRecordMetadata().partition());
        log.info("                 Offset:    {}", result.getRecordMetadata().offset());

/*
        // Отсылка сообщения с использованием CompletableFuture
        CompletableFuture<SendResult<String, ProductCreateEvent>> future =
                kafkaTemplate.send(productCreateTopic, productId, productCreateEvent);
        future.whenComplete((result, ex) -> {
            if (ex != null) {
                log.error("Error sending message: {}", ex.getMessage());
            } else {
                log.info("Message sent successfully: {}", result.getRecordMetadata());
            }
        });
        future.join();
        log.info("Product created: {}", productId);
*/
        return productId;
    }

}
