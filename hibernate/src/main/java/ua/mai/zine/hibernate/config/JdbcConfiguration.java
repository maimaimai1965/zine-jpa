package ua.mai.zine.hibernate.config;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.jooq.DSLContext;
import org.jooq.ExecuteListenerProvider;
import org.jooq.SQLDialect;
import org.jooq.conf.MappedSchema;
import org.jooq.conf.RenderMapping;
import org.jooq.conf.Settings;
import org.jooq.impl.DSL;
import org.jooq.impl.DataSourceConnectionProvider;
import org.jooq.impl.DefaultConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jooq.SpringTransactionProvider;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;
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

    @Bean("jooqSettings")
    public Settings jooqSettings(@Value("${app.db.schema}") String schema) {
        return new Settings()
                .withRenderMapping(new RenderMapping()
                        .withDefaultSchema(schema)
//                        .withSchemata(
//                                new MappedSchema().withInput("AA").withOutput(schema),
//                                new MappedSchema().withInput("MR").withOutput(schema),
//                                new MappedSchema().withInput("OAM").withOutput(schema),
//                                new MappedSchema().withInput("PRS").withOutput(schema))
                        );
    }

    @Bean
    public DSLContext dslContext(DefaultHikariConfig config,
                                 @Value("${spring.datasource.url}") String url,
                                 @Value("${spring.datasource.username}") String username,
                                 @Value("${spring.datasource.password}") String password,
                                 @Qualifier("jooqSettings") Settings settings) {
        config.setJdbcUrl(url);
        config.setUsername(username);
        config.setPassword(password);
        DataSource dataSource = new HikariDataSource(config);

        PlatformTransactionManager transactionManager = new JdbcTransactionManager(dataSource);

        return DSL.using(new DefaultConfiguration()
                .derive(settings)
                .derive(SQLDialect.POSTGRES)
//                .derive(executeListenerProvider)
                .derive(new DataSourceConnectionProvider(new TransactionAwareDataSourceProxy(dataSource)))
                .derive(new SpringTransactionProvider(transactionManager))
        );
    }

}