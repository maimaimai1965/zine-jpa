package ua.mai.zine.kafka.app;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import ua.mai.zine.kafka.app.config.DefaultProfileUtil;
import ua.mai.zine.kafka.producer.config.ProducerConfiguration;
import ua.mai.zine.kafka.producer.controller.ProductController;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * The entry point of the Spring Boot application.
 *
 * Use the @PWA annotation make the application installable on phones, tablets
 * and some desktop browsers.
 *
 */
@SpringBootApplication
        (scanBasePackageClasses = {
                ProducerConfiguration.class
//                JpaBaseClassConfiguration.class,
        }, exclude = {
//                SecurityAutoConfiguration.class,
//                ErrorMvcAutoConfiguration.class,
        })
@EnableConfigurationProperties
 ({
//    AaProperties.class,
 })
public class ProducerApplication {

    private static final Logger log = LoggerFactory.getLogger(ProducerApplication.class);

    private final Environment env;

    public ProducerApplication(Environment env) {
        this.env = env;
    }

    public static void main(String[] args) {
        System.getProperties().setProperty("org.jooq.no-logo", "true");
        System.getProperties().setProperty("spring.application.name", "Producer Application");
        SpringApplication app = new SpringApplication(ProducerApplication.class);
        DefaultProfileUtil.addDefaultProfile(app);
        try {
            ConfigurableApplicationContext context = app.run(args);
            Environment env = context.getEnvironment();
            logApplicationStartup(env);

            checkRepository(context);
//            checkService(context);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void logApplicationStartup(Environment env) {
        String protocol = "http";
        if (env.getProperty("server.ssl.key-store") != null) {
            protocol = "https";
        }
        String serverPort = env.getProperty("server.port");
        String contextPath = env.getProperty("server.servlet.context-path");
        if (StringUtils.isBlank(contextPath)) {
            contextPath = "/";
        }
        String hostAddress = "localhost";
        try {
            hostAddress = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            log.warn("The host name could not be determined, using `localhost` as fallback");
        }
        log.info("\n----------------------------------------------------------\n\t" +
                        "Application '{}' is running! Access URLs:\n\t" +
                        "Local: \t\t{}://localhost:{}{}\n\t" +
                        "External: \t{}://{}:{}{}\n\t" +
                        "Profile(s): \t{}\n----------------------------------------------------------",
                env.getProperty("spring.application.name"),
                protocol,
                serverPort,
                contextPath,
                protocol,
                hostAddress,
                serverPort,
                contextPath,
                env.getActiveProfiles());
    }

    private static void checkRepository(ConfigurableApplicationContext context) {
        ProductController productController = (ProductController)context.getBean("productController");
        int i = 1;
    }

    private static void checkService(ConfigurableApplicationContext context) {
//        FoodService foodService = (FoodService)context.getBean("foodService");
//
////        Animal animal = foodService.findAnimal(1L);
////        Food food = foodService.findFood(1);
//
//        Long animalId = 1L;
//        Integer foodId = 1;
//        FoodOrderDto foodOrderDto = FoodOrderDto.builder()
//                .animalId(animalId)
//                .foodId(foodId)
//                .amount(40.)
//                .orderDt(LocalDate.now())
//                .build();
//        FoodOrderDto resultFoodOrderDto = foodService.addFoodOrder(foodOrderDto);
//
//        int i = 1;
    }

}

