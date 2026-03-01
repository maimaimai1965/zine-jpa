package ua.mai.zine.kafka.app.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ConsumerApplicationConfiguration {

//    @Bean
//    public ProducerFactory<String, Object> producerFactory(
//            @Value("${spring.kafka.bootstrap-servers}") String bootstrapServers,
////            @Value("${spring.kafka.listener.ack-mode}") String ackMode,
//            @Value("${spring.kafka.producer.key-serializer}") String keySerializer,
//            @Value("${spring.kafka.producer.value-serializer}") String valueSerializer,
//            @Value("${spring.kafka.producer.enable-idempotence}") String enableIdempotence,
//            @Value("${spring.kafka.producer.acks}") String acks,
//            @Value("${spring.kafka.producer.retries}") String retries,
//            @Value("${spring.kafka.producer.properties.max.in.flight.requests.per.connection}") String maxInFlightRequestsPerConnection,
//            @Value("${spring.kafka.producer.properties.retry.backoff.ms}") String retryBackoffMs,
//            @Value("${spring.kafka.producer.properties.delivery-timeout-ms}") String deliveryTimeoutMs,
//            @Value("${spring.kafka.producer.properties.request.timeout.ms}") String requestTimeoutMs) {
//
//        Map<String, Object> configs = new HashMap<>();
//
//        configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
//        configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, keySerializer);
//        configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, valueSerializer);
//        configs.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, enableIdempotence);
//        configs.put(ProducerConfig.ACKS_CONFIG, acks);
//        configs.put(ProducerConfig.RETRIES_CONFIG, retries);
//        configs.put(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION, maxInFlightRequestsPerConnection);
//        configs.put(ProducerConfig.RETRY_BACKOFF_MS_CONFIG, retryBackoffMs);
//        configs.put(ProducerConfig.DELIVERY_TIMEOUT_MS_CONFIG, deliveryTimeoutMs);
//        configs.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, requestTimeoutMs);
//
//        return new DefaultKafkaProducerFactory<>(configs);
//    }
//
//    @Bean
//    public KafkaTemplate kafkaTemplate(ProducerFactory<String, Object> producerFactory) {
//        return new KafkaTemplate<>(producerFactory);
//    }
//
//    @Bean
//    public NewTopic createTopic(@Value("${zine.kafka.product-create-topic.name}") String topicName,
//                                @Value("${zine.kafka.product-create-topic.partitions}") int partitions) {
//        return TopicBuilder
//                .name(topicName)
//                .partitions(partitions)
////                .replicas(3)
////                .configs(Map.of("min.isync.replicas", "2"))
//                .build();
//    }

}
