package ua.mai.zine.hibernate;

import jakarta.persistence.EntityManagerFactory;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
//import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;
import ua.mai.zine.hibernate.config.DefaultProfileUtil;
import ua.mai.zine.hibernate.config.JpaConfiguration;

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
                JpaConfiguration.class
//                HibernateConfiguration.class,
//                JpaBaseClassConfiguration.class,
//                JdbcConfiguration.class,
        }, exclude = {
                HibernateJpaAutoConfiguration.class
//                SecurityAutoConfiguration.class,
//                ErrorMvcAutoConfiguration.class,
        })
@EnableConfigurationProperties
 ({
//    AaProperties.class,
 })
public class HibernateApplication {

    private static final Logger log = LoggerFactory.getLogger(HibernateApplication.class);

    private final Environment env;

    public HibernateApplication(Environment env) {
        this.env = env;
    }

    public static void main(String[] args) {
        System.getProperties().setProperty("org.jooq.no-logo", "true");
        System.getProperties().setProperty("spring.application.name", "Hibernate Backend Services");
        SpringApplication app = new SpringApplication(HibernateApplication.class);
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

    public static SessionFactory getSessionFactory(ConfigurableApplicationContext context) {
        // Вариант A: через EntityManagerFactory (рекомендуется)
        EntityManagerFactory emf = context.getBean(EntityManagerFactory.class);
        return emf.unwrap(SessionFactory.class);

        // Вариант B: если вы явно создали бин SessionFactory с именем "sessionFactory"
        // return context.getBean(SessionFactory.class);
        // или
        // return context.getBean("sessionFactory", SessionFactory.class);
    }

    private static void checkRepository(ConfigurableApplicationContext context) {
//        SessionFactory sessionFactory = (SessionFactory)context.getBean("zineSessionFactory");
        SessionFactory sessionFactory = (SessionFactory)getSessionFactory(context);
        sessionFactory.getCurrentSession();
        sessionFactory.close();

//        AnimalTypeRepository animalTypeRepository = (AnimalTypeRepository)context.getBean("animalTypeRepository");
//        Optional<AnimalType> animalType = animalTypeRepository.findById(1);

        int i = 1;
    }

    private static void checkService(ConfigurableApplicationContext context) {
//        FoodService foodService = (FoodService)context.getBean("foodService");
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

        int i = 1;
    }

}

