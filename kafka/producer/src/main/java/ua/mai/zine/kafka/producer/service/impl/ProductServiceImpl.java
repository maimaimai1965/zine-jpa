package ua.mai.zine.kafka.producer.service.impl;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import ua.mai.zine.kafka.producer.dto.CreateProductDto;
import ua.mai.zine.kafka.producer.event.ProductCreatedEvent;
import ua.mai.zine.kafka.producer.service.ProductService;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    public static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final KafkaTemplate<String, ProductCreatedEvent> kafkaTemplate;

    @Override
    public String createProduct(CreateProductDto createProductDto) {
        // TODO save to DB

        String productId = UUID.randomUUID().toString();

        ProductCreatedEvent productCreatedEvent =
                new ProductCreatedEvent(productId, createProductDto.getTitle(), createProductDto.getPrice(), createProductDto.getQuantity());

        CompletableFuture<SendResult<String, ProductCreatedEvent>> future =
                kafkaTemplate.send("product-create-topic", productId, productCreatedEvent);

        future.whenComplete((result, ex) -> {
            if (ex != null) {
                log.error("Error sending message: {}", ex.getMessage());
            } else {
                log.info("Message sent successfully: {}", result.getRecordMetadata());
            }
        });

        log.info("Product created: {}", productId);

        return productId;
    }
}
