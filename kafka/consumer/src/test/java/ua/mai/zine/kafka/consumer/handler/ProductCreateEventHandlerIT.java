package ua.mai.zine.kafka.consumer.handler;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;
import ua.mai.zine.kafka.consumer.config.ConsumerConfiguration;
import ua.mai.zine.kafka.consumer.initializer.Postgres;
import ua.mai.zine.kafka.consumer.persistence.entity.ProcessedEventEntity;
import ua.mai.zine.kafka.consumer.repository.ProcessEventRepository;
import ua.mai.zine.kafka.event.ProductCreateEvent;

import java.math.BigDecimal;
import java.util.TimeZone;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@EmbeddedKafka()
@ActiveProfiles("test")
@ContextConfiguration(initializers = {
        Postgres.Initializer.class
})
@Transactional
@SpringBootTest(properties = "spring.kafka.consumer.bootstrap-servers=${spring.embedded.kafka.brokers}")
class ProductCreateEventHandlerIT {

    @Configuration
    @EnableAutoConfiguration
    @Import(ConsumerConfiguration.class)
    static class TestConfig {
    }

    @MockBean
    ProcessEventRepository processEventRepository;

    @Autowired
    KafkaTemplate<String, Object> kafkaTemplate;

    @SpyBean
    ProductCreateEventHandler productCreateEventHandler;

    @BeforeAll
    static void init() {
//        TimeZone.setDefault(java.util.TimeZone.getTimeZone("UTC"));
        Postgres.container.start();
    }

    @Test
    public void handle_OnProductCreateEvent_handleEvent() throws ExecutionException, InterruptedException {
        // Arrange
        ProductCreateEvent productCreateEvent = new ProductCreateEvent(
                UUID.randomUUID().toString(),
                "Test product",
                new BigDecimal(10),
                2);
        String message = UUID.randomUUID().toString();
        String messageKey = productCreateEvent.productId();

        ProducerRecord<String, Object> record = new ProducerRecord<>(
                "product-create-topic",
                messageKey,
                productCreateEvent);
        record.headers().add("messageId", message.getBytes());
        record.headers().add(KafkaHeaders.RECEIVED_KEY, messageKey.getBytes());

        ProcessedEventEntity processedEventEntity = new ProcessedEventEntity();
        when(processEventRepository.findByMessageId(anyString())).thenReturn(processedEventEntity);
        when(processEventRepository.save(ArgumentMatchers.any(ProcessedEventEntity.class))).thenReturn(null);

        // Act
        kafkaTemplate.send(record).get();

        // Assert
        ArgumentCaptor<String> messageIdCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> messageKeyCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<ProductCreateEvent> eventCaptor = ArgumentCaptor.forClass(ProductCreateEvent.class);

        verify(productCreateEventHandler, timeout(5_000).times(1))
                .handle(eventCaptor.capture(),
                        messageIdCaptor.capture(),
                        messageKeyCaptor.capture());

        assertEquals(message, messageIdCaptor.getValue());
        assertEquals(messageKey, messageKeyCaptor.getValue());
        assertEquals(productCreateEvent.productId(), eventCaptor.getValue().productId());
    }
  
}