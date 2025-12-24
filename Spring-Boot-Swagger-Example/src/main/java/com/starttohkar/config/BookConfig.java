package com.starttohkar.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.OpenAPI;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BookConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Book Service")
                        .description("Sample Documentation Generated Using OpenAPI 3 for our Book Rest API")
                        .version("1.0")
                        .termsOfService("https://www.youtube.com/channel/UCORuRdpN2QTCKnsuEaeK-kQ")
                        .license(new License()
                                .name("Java_Gyan_Mantra License")
                                .url("https://www.youtube.com/channel/UCORuRdpN2QTCKnsuEaeK-kQ")));
    }

    @Bean
    public GroupedOpenApi bookApi() {
        return GroupedOpenApi.builder()
                .group("Java Techie")
                .pathsToMatch("/book/**")
                .build();
    }
}