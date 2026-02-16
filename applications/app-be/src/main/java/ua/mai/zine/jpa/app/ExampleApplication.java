package ua.mai.zine.jpa.app;

import jakarta.persistence.EntityManagerFactory;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ua.mai.zine.jpa.boot.BeApplication;
import ua.mai.zine.jpa.zoo.entity.animal.AnimalRepository;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * The entry point of the Spring Boot application.
 *
 * Use the @PWA annotation make the application installable on phones, tablets
 * and some desktop browsers.
 *
 */

//@SpringBootApplication
@EnableJpaRepositories(
        basePackages = "ua.mai.zine.jpa",
        repositoryBaseClass = ua.mai.zine.jpa.repository.BaseRepositoryImpl.class
)
public class ExampleApplication {



    public static void main(String[] args) {

//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("ua.mai.zine.jpa");
//        SessionFactory sessionFactory = getSessionFactory(context);
//        Session session = sessionFactory.openSession();
//        session.close();

        SpringApplication app = new SpringApplication(ExampleApplication.class);
        app.setWebApplicationType(WebApplicationType.NONE);

        AnnotationConfigApplicationContext context =
                (AnnotationConfigApplicationContext) app.run(args);
//        AnnotationConfigApplicationContext context = (AnnotationConfigApplicationContext)
//                        SpringApplication.run(ExampleApplication.class, args);

        AnimalRepository animalRepository = context.getBean(AnimalRepository.class);

    }

    public static SessionFactory getSessionFactory(AnnotationConfigApplicationContext context) {
        // Вариант A: через EntityManagerFactory (рекомендуется)
        EntityManagerFactory emf = context.getBean(EntityManagerFactory.class);
        return emf.unwrap(SessionFactory.class);

        // Вариант B: если вы явно создали бин SessionFactory с именем "sessionFactory"
        // return context.getBean(SessionFactory.class);
        // или
        // return context.getBean("sessionFactory", SessionFactory.class);
    }

}

