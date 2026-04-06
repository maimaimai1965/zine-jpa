package ua.mai.zine.kafka.consumer.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan("ua.mai.zine.kafka.consumer")
@EnableJpaRepositories(basePackages = "ua.mai.zine.kafka.consumer.repository")
@EntityScan(basePackages = "ua.mai.zine.kafka.consumer.persistence.entity")
public class ConsumerConfiguration {
}
