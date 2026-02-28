package ua.mai.zine.kafka.producer.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ua.mai.zine.kafka.producer.controller.ProductController;

@Configuration
@ComponentScan("ua.mai.zine.kafka.producer")
public class ProducerConfiguration {
}
