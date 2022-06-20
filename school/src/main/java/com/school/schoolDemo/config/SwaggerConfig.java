package com.school.schoolDemo.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "SpringBoot Student Management",
                description = "This application allows to save student details in the database and alter those details as per need",
                contact = @Contact(
                        name = "Uttam Prakash",
                        url = "http://localhost:8080/swagger-ui/index.html#/",
                        email = "uttamprakash2307@gmail.com"
                ),
        license = @License(
                name = "Not Licensed",
                url = "Null"
        )
        ),
        servers = @Server(url = "http://localhost:8080")
)
public class SwaggerConfig {
    @Bean
    public GroupedOpenApi publicApi(){
        return GroupedOpenApi.builder()
                .group("student-public")
                .pathsToMatch("/student/*", "/student/*/*")
                .build();
    }

    @Bean
    public GroupedOpenApi userApi(){
        return GroupedOpenApi.builder()
                .group("user-api")
                .pathsToMatch("/user/**")
                .build();
    }

}
