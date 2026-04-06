package ua.mai.zine.kafka.consumer.handler;

import lombok.NonNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ua.mai.zine.kafka.consumer.exception.NonRetryableException;
import ua.mai.zine.kafka.consumer.exception.RetryableException;
import ua.mai.zine.kafka.consumer.persistence.entity.ProcessedEventEntity;
import ua.mai.zine.kafka.consumer.repository.ProcessEventRepository;
import ua.mai.zine.kafka.event.ProductCreateEvent;

import java.util.HashMap;
import java.util.Map;

@Component
@KafkaListener(topics = "${zine.kafka.product-create-topic.name}")
public class ProductCreateEventHandler {

    private static final Logger log = LoggerFactory.getLogger(ProductCreateEventHandler.class);

    private ProcessEventRepository processEventRepository;

    public ProductCreateEventHandler(@NonNull ProcessEventRepository processEventRepository) {
        this.processEventRepository = processEventRepository;
    }

    @Transactional
    @KafkaHandler
    public void handle(@Payload ProductCreateEvent productCreateEvent,
                       @Header("messageId") String messageId,
                       @Header(KafkaHeaders.RECEIVED_KEY) String messageKey) {

        log.info("Received ProductCreateEvent: {} {}", productCreateEvent.productId(), productCreateEvent.title());

        if (productCreateEvent.title().equals("RetryableException"))
            throw new RetryableException("RetryableException");
        else
        if (productCreateEvent.title().equals("NonRetryableException"))
            throw new NonRetryableException("NonRetryableException move into DLQ");

        ProcessedEventEntity processedEventEntity = processEventRepository.findByMessageId(messageId);

        if (processedEventEntity != null) {
            log.info("Duplicate message id: {}", processedEventEntity);
            return;
        }

        log.info("... some operation after getting message ...");

        try {
            processEventRepository.save(new ProcessedEventEntity(messageId, productCreateEvent.productId()));
        } catch (DataIntegrityViolationException e) {
            log.error(e.getMessage(), e);
        }

    }

}
