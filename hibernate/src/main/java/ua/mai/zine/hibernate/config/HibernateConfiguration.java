package ua.mai.zine.hibernate.config;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import jakarta.persistence.EntityManagerFactory;

//@org.springframework.context.annotation.Configuration
public class HibernateConfiguration {

    @Value("${spring.datasource.hikari.jdbc-url}")
    private String url;

    @Value("${spring.datasource.hikari.username:root}")
    private String username;

    @Value("${spring.datasource.hikari.password:root}")
    private String password;

    @Value("${spring.datasource.hikari.driver-class-name:com.mysql.cj.jdbc.Driver}")
    private String driverClassName;

    @Bean("zineSessionFactory")
    public SessionFactory sessionFactory() {
        Configuration configuration = new Configuration();

        configuration
                .addPackage("ua.mai.zine.jpa.zoo.entity")
                .setProperty(Environment.DRIVER, driverClassName)
                .setProperty(Environment.URL, url)
                .setProperty(Environment.USER, username)
                .setProperty(Environment.PASS, password)
                .setProperty(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect")
                .setProperty(Environment.SHOW_SQL, "true")
                .setProperty(Environment.FORMAT_SQL, "true")
                .setProperty(Environment.HBM2DDL_AUTO, "none");
//        Properties settings = new Properties();
//        settings.put(Environment.DRIVER, driverClassName);
//        settings.put(Environment.URL, url);
//        settings.put(Environment.USER, username);
//        settings.put(Environment.PASS, password);
//        settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
//
//        settings.put(Environment.SHOW_SQL, "true");
//        settings.put(Environment.FORMAT_SQL, "true");
//        settings.put(Environment.HBM2DDL_AUTO, "none");
//
//        configuration.setProperties(settings);
//
//        configuration.addAnnotatedClass(Animal.class);
//        configuration.addAnnotatedClass(AnimalType.class);
//        configuration.addAnnotatedClass(Tank.class);
//        configuration.addAnnotatedClass(TankAnimal.class);

        return configuration.buildSessionFactory();
    }

    @Bean("entityManagerFactory")
    public EntityManagerFactory entityManagerFactory(@Qualifier("zineSessionFactory") SessionFactory sessionFactory) {
        return sessionFactory;
    }
}
