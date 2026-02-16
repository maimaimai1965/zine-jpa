package ua.mai.zine.jpa.boot;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.autoconfigure.mail.MailSenderAutoConfiguration;
//import org.springframework.boot.autoconfigure.quartz.QuartzAutoConfiguration;
//import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
//import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import ua.mai.zine.jpa.boot.config.DefaultProfileUtil;
import ua.mai.zine.jpa.config.JpaBaseClassConfiguration;
import ua.mai.zine.jpa.config.JpaZooConfiguration;
import ua.mai.zine.jpa.zoo.entity.animal.Animal;
import ua.mai.zine.jpa.zoo.entity.animal.AnimalRepository;
import ua.mai.zine.jpa.zoo.entity.animal_type.AnimalType;
import ua.mai.zine.jpa.zoo.entity.animal_type.AnimalTypeRepository;
import ua.mai.zine.jpa.zoo.entity.tank.Tank;
import ua.mai.zine.jpa.zoo.entity.tank.TankRepository;
import ua.mai.zine.jpa.zoo.entity.tank_animal.TankAnimal;
import ua.mai.zine.jpa.zoo.entity.tank_animal.TankAnimalRepository;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Optional;

/**
 * The entry point of the Spring Boot application.
 *
 * Use the @PWA annotation make the application installable on phones, tablets
 * and some desktop browsers.
 *
 */
@SpringBootApplication
        (scanBasePackageClasses = {
                JpaBaseClassConfiguration.class,
                JpaZooConfiguration.class,
//                JdbcConfiguration.class,
        }, exclude = {
//                SecurityAutoConfiguration.class,
//                ErrorMvcAutoConfiguration.class,
        })
@EnableConfigurationProperties
 ({
//    AaProperties.class,
 })
public class BeApplication {

    private static final Logger log = LoggerFactory.getLogger(BeApplication.class);

    private final Environment env;

    public BeApplication(Environment env) {
        this.env = env;
    }

    public static void main(String[] args) {
        System.getProperties().setProperty("org.jooq.no-logo", "true");
        System.getProperties().setProperty("spring.application.name", "Zoo Backend Services");
        SpringApplication app = new SpringApplication(BeApplication.class);
        DefaultProfileUtil.addDefaultProfile(app);
        try {
            ConfigurableApplicationContext context = app.run(args);
            Environment env = context.getEnvironment();
            logApplicationStartup(env);

            AnimalTypeRepository animalTypeRepository = (AnimalTypeRepository)context.getBean("animalTypeRepository");
            Optional<AnimalType> animalType = animalTypeRepository.findById(1);

            AnimalRepository animalRepository = (AnimalRepository)context.getBean("animalRepository");
            Optional<Animal> animal = animalRepository.findById(1L);

            TankRepository tankRepository = (TankRepository)context.getBean("tankRepository");
            Optional<Tank> tank = tankRepository.findById(1);

            TankAnimalRepository tankAnimalRepository = (TankAnimalRepository)context.getBean("tankAnimalRepository");
            Optional<TankAnimal> tankAnimal = tankAnimalRepository.findById(1L);

            int i = 1;

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
}

