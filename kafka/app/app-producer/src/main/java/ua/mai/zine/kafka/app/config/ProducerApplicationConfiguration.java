package ua.mai.zine.kafka.app.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class ProducerApplicationConfiguration {

//    @Bean
//    public KafkaAdmin kafkaAdmin(@Value("${spring.kafka.bootstrap-servers}") String bootstrapServers) {
//        Map<String, Object> configs = new HashMap<>();
//        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
//
//        return new KafkaAdmin(configs);
//    }

    @Bean
    public NewTopic createTopic() {
        return TopicBuilder
                .name("product-create-topic")
                .partitions(3)
//                .replicas(3)
//                .configs(Map.of("min.isync.replicas", "2"))
                .build();
    }
}
