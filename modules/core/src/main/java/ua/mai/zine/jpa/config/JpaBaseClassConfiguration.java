package ua.mai.zine.jpa.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(
        repositoryBaseClass = ua.mai.zine.jpa.repository.BaseRepositoryImpl.class
)
public class JpaBaseClassConfiguration {
}
