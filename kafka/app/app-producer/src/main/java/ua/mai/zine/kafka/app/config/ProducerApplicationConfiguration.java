package ua.mai.zine.kafka.app.config;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ProducerApplicationConfiguration {

    @Autowired
    Environment environment;

    @Bean
    public ProducerFactory<String, Object> producerFactory() {

        Map<String, Object> configs = new HashMap<>();

        configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,      environment.getProperty("spring.kafka.bootstrap-servers"));
        configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,   environment.getProperty("spring.kafka.producer.key-serializer"));
        configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, environment.getProperty("spring.kafka.producer.value-serializer"));
        configs.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG,     environment.getProperty("spring.kafka.producer.enable-idempotence"));
        configs.put(ProducerConfig.ACKS_CONFIG,                   environment.getProperty("spring.kafka.producer.acks"));
        configs.put(ProducerConfig.RETRIES_CONFIG,                environment.getProperty("spring.kafka.producer.retries"));
        configs.put(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION, environment.getProperty("spring.kafka.producer.properties.max.in.flight.requests.per.connection"));
        configs.put(ProducerConfig.RETRY_BACKOFF_MS_CONFIG,       environment.getProperty("spring.kafka.producer.properties.retry.backoff.ms"));
        configs.put(ProducerConfig.DELIVERY_TIMEOUT_MS_CONFIG,    environment.getProperty("spring.kafka.producer.properties.delivery-timeout-ms"));
        configs.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG,     environment.getProperty("spring.kafka.producer.properties.request.timeout.ms"));

        return new DefaultKafkaProducerFactory<>(configs);
    }

    @Bean
    public KafkaTemplate kafkaTemplate(ProducerFactory<String, Object> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }

    @Bean
    public NewTopic createTopic(@Value("${zine.kafka.product-create-topic.name}") String topicName,
                                @Value("${zine.kafka.product-create-topic.partitions}") int partitions,
                                @Value("${zine.kafka.product-create-topic.replicas}") int replicas,
                                @Value("${zine.kafka.product-create-topic.min-isync-replicas}") int minIsyncReplicas) {
        return TopicBuilder
                .name(topicName)
                .partitions(partitions)
//                .replicas(3)                                  // Всего серверов
//                .configs(Map.of("min.isync.replicas", "2"))   // Сколько серверов должны подтвердить
                .build();
    }

}
