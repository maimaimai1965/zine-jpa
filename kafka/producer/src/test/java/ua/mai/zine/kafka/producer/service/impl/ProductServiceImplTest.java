package ua.mai.zine.kafka.producer.service.impl;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.ContainerTestUtils;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import ua.mai.zine.kafka.dto.ProductCreateDto;
import ua.mai.zine.kafka.event.ProductCreateEvent;
import ua.mai.zine.kafka.producer.config.ProducerConfiguration;
import ua.mai.zine.kafka.producer.service.ProductService;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@DirtiesContext // Пересоздается контекст для теста, если предыдущий тест изменил контекст.
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
@EmbeddedKafka(partitions = 1, count = 1, controlledShutdown = true)
@SpringBootTest(
    properties = "spring.kafka.producer.bootstrap-servers=${spring.embedded.kafka.brokers}",
    classes = ProductServiceImplTest.TestConfig.class
)
class ProductServiceImplTest {

    @Configuration
    @EnableAutoConfiguration
    @Import(ProducerConfiguration.class)
    static class TestConfig {
    }

    @Autowired
    private ProductService productService;

    @Autowired
    private Environment environment;

    @Autowired
    private EmbeddedKafkaBroker embeddedKafkaBroker;

    private KafkaMessageListenerContainer<String, ProductCreateEvent> container;

    private BlockingQueue<ConsumerRecord<String, ProductCreateEvent>> records;

    @BeforeEach
    void setUp() {
        DefaultKafkaConsumerFactory<String, Object> consumerFactory =
                new DefaultKafkaConsumerFactory(getConsumerProperties());
        ContainerProperties containerProperties =
                new ContainerProperties(environment.getProperty("product-create-events-topic-name")); // свойство из application-test.properties
        container = new KafkaMessageListenerContainer<>(consumerFactory, containerProperties);

        records = new LinkedBlockingQueue<>();
        container.setupMessageListener((MessageListener<String, ProductCreateEvent>)records::add);
        container.start();
        ContainerTestUtils.waitForAssignment(container, embeddedKafkaBroker.getPartitionsPerTopic());
    }

    private Map<String, Object> getConsumerProperties() {
        return Map.of(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, embeddedKafkaBroker.getBrokersAsString(),
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class,
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class,
                ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, JsonDeserializer.class,
                JsonDeserializer.TRUSTED_PACKAGES, environment.getProperty("spring.kafka.consumer.properties.spring.json.trusted.packages"),
                ConsumerConfig.GROUP_ID_CONFIG, environment.getProperty("spring.kafka.consumer.group-id"),
                ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, environment.getProperty("spring.kafka.consumer.auto-offset-reset")
        );
    }

    @AfterAll
    void tearDown() {
        container.stop();
    }

    @Test
    void testCreateProduct_whenGivenValidProductDetail_succesfulySendKafkaMessageIT() throws ExecutionException, InterruptedException {
        // Arrange
        String title = "Sumsung";
        BigDecimal price = new BigDecimal(1000);
        Integer quantity = 1;
        ProductCreateDto productCreateDto = new ProductCreateDto(title, price, quantity);

        // Act
        productService.createProduct(productCreateDto);

        // Assert
        ConsumerRecord<String, ProductCreateEvent> message = records.poll(2000, TimeUnit.MILLISECONDS);
        System.out.println("[DEBUG_LOG] Polled message: " + message);
        Assertions.assertNotNull(message);
        Assertions.assertNotNull(message.key());
        ProductCreateEvent productCreateEvent = message.value();
        Assertions.assertEquals(title, productCreateEvent.title());
        Assertions.assertEquals(price, productCreateEvent.price());
        Assertions.assertEquals(quantity, productCreateEvent.quantity());
    }

}