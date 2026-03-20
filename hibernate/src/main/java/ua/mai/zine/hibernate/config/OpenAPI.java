package ua.mai.zine.hibernate.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class OpenAPI {
    @Bean
    public io.swagger.v3.oas.models.OpenAPI schedulerOpenAPI() {
        return new io.swagger.v3.oas.models.OpenAPI()
                .info(new Info()
                        .title("Zine Hibernate API")
                        .version("1.0"))
                .addSecurityItem(new SecurityRequirement().addList("basicAuth"))
                .components(new io.swagger.v3.oas.models.Components()
                        .addSecuritySchemes("basicAuth", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("basic")
                                .description("Basic Authentication")));
    }
}
