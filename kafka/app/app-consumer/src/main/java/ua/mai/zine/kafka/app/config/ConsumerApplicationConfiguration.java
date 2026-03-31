package ua.mai.zine.kafka.app.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;
import ua.mai.zine.kafka.consumer.exception.NonRetryableException;

import java.util.HashMap;
import java.util.Map;
import org.springframework.util.backoff.FixedBackOff;
import ua.mai.zine.kafka.consumer.exception.RetryableException;

@Configuration
public class ConsumerApplicationConfiguration {

    private static final Logger log = LoggerFactory.getLogger(ConsumerApplicationConfiguration.class);
    private static final String DLT_SUFFIX = ".DLT";

    @Autowired
    Environment environment;

    @Bean
    public ConsumerFactory<String, Object> consumerFactory() {

        Map<String, Object> configs = new HashMap<>();

        configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,        environment.getProperty("spring.kafka.bootstrap-servers"));

        configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
        configs.put(ErrorHandlingDeserializer.KEY_DESERIALIZER_CLASS, StringDeserializer.class);

        configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
        configs.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, JsonDeserializer.class);

        configs.put(JsonDeserializer.TRUSTED_PACKAGES,              environment.getProperty("spring.kafka.consumer.properties.spring.json.trusted.packages"));
        configs.put(ConsumerConfig.GROUP_ID_CONFIG,                 environment.getProperty("spring.kafka.consumer.group-id"));

        return new DefaultKafkaConsumerFactory<>(configs);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Object> kafkaListenerContainerFactory(
            ConsumerFactory<String, Object> consumerFactory,
            DeadLetterPublishingRecoverer deadLetterPublishingRecoverer) {

        ConcurrentKafkaListenerContainerFactory<String, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);

        // Retry 3 times with 1 second delay, then publish record to <topic>.DLT.
        DefaultErrorHandler errorHandler =
                new DefaultErrorHandler(deadLetterPublishingRecoverer, new FixedBackOff(1000L, 3L));
        errorHandler.addRetryableExceptions(RetryableException.class);
        errorHandler.addNotRetryableExceptions(NonRetryableException.class);
        // Show retray
        errorHandler.setRetryListeners((record, ex, deliveryAttempt) ->
                log.warn(
                        "Retry attempt #{} for {}-{}@{} due to {}: {}",
                        deliveryAttempt,
                        record.topic(),
                        record.partition(),
                        record.offset(),
                        ex.getClass().getSimpleName(),
                        ex.getMessage()
                )
        );

        factory.setCommonErrorHandler(errorHandler);

        return factory;
    }

    @Bean
    public DeadLetterPublishingRecoverer deadLetterPublishingRecoverer(KafkaTemplate<String, Object> kafkaTemplate) {
        return new DeadLetterPublishingRecoverer(
                kafkaTemplate,
                (record, ex) -> new TopicPartition(record.topic() + DLT_SUFFIX, record.partition())
        );
    }

    /* Producer для DSL topic */
    @Bean
    public ProducerFactory<String, Object> producerFactory() {

        Map<String, Object> configs = new HashMap<>();

        configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,      environment.getProperty("spring.kafka.bootstrap-servers"));
        configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,   StringSerializer.class);
        configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        return new DefaultKafkaProducerFactory<>(configs);
    }

    @Bean
    public KafkaTemplate<String, Object> kafkaTemplate(ProducerFactory<String, Object> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }

}
