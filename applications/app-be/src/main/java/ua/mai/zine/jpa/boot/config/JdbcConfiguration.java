package ua.mai.zine.jpa.boot.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.support.JdbcTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;


@Configuration
@EnableConfigurationProperties({
        JdbcConfiguration.DefaultHikariConfig.class
})
public class JdbcConfiguration {

    @ConfigurationProperties(prefix="spring.datasource.hikari")
    public static class DefaultHikariConfig extends HikariConfig {}


    @Bean
    @Primary
    public DataSource hikariDefaultDatasource(DefaultHikariConfig config) {
        return new HikariDataSource(config);
    }

    @Bean
    @Primary
    public PlatformTransactionManager defaultTransactionManager(DataSource dataSource) {
        return new JdbcTransactionManager(dataSource);
    }

}
