//package ua.mai.config;
//
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import javax.sql.DataSource;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Objects;
//
//@Configuration
//@EnableTransactionManagement
//@EnableJpaRepositories(
//        basePackageClasses = DefaultJpaConfiguration.class,
//        entityManagerFactoryRef = "defaultEntityManagerFactory",
//        transactionManagerRef = "defaultTransactionManager"
//)
//public class DefaultJpaConfiguration {
//
//    @Primary
//    @Bean
//    public LocalContainerEntityManagerFactoryBean defaultEntityManagerFactory(
//            DataSource dataSource,
//            EntityManagerFactoryBuilder builder,
//            @Qualifier("defaultJpaProperties") Map<String, ?> jpaProperties) {
//        return builder
//                .dataSource(dataSource)
//                .packages(DefaultJpaConfiguration.class)
//                .properties(jpaProperties)
//                .build();
//    }
//
//    @Primary
//    @Bean
//    public PlatformTransactionManager defaultTransactionManager(
//            @Qualifier("defaultEntityManagerFactory")
//            LocalContainerEntityManagerFactoryBean defaultEntityManagerFactory) {
//        return new JpaTransactionManager(Objects.requireNonNull(defaultEntityManagerFactory.getObject()));
//    }
//
//    @Primary
//    @Bean
//    @ConfigurationProperties(prefix = "spring.jpa.properties")
//    public Map<String, ?> defaultJpaProperties() {
//        return new HashMap<>();
//    }
//}
