package ua.mai.zine.kafka.app.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ConsumerApplicationConfiguration {

    @Autowired
    Environment environment;

    @Bean
    public ConsumerFactory<String, Object> producerFactory() {

        Map<String, Object> configs = new HashMap<>();

        configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,        environment.getProperty("spring.kafka.bootstrap-servers"));
        configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,   environment.getProperty("spring.kafka.consumer.key-deserializer"));
        configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,   environment.getProperty("spring.kafka.consumer.key-deserializer"));
        configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, environment.getProperty("spring.kafka.consumer.value-deserializer"));
        configs.put(JsonDeserializer.TRUSTED_PACKAGES,              environment.getProperty("spring.kafka.consumer.properties.spring.json.trusted.packages"));
        configs.put(ConsumerConfig.GROUP_ID_CONFIG,                 environment.getProperty("spring.kafka.consumer.group-id"));

        return new DefaultKafkaConsumerFactory<>(configs);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Object> kafkaListenerContainerFactory(
            ConsumerFactory<String, Object> consumerFactory) {
        ConcurrentKafkaListenerContainerFactory<String, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        return factory;
    }

}
