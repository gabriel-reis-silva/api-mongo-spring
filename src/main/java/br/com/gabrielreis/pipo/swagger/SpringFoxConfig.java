package br.com.gabrielreis.pipo.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;


@Configuration
public class SpringFoxConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.gabrielreis.pipo"))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(List.of(apiKey()))
                .apiInfo(metaInfo());
    }

    private ApiKey apiKey() {
        return new ApiKey("Bearer token", "Authorization", "header");
    }

    private ApiInfo metaInfo() {
        ApiInfo apiInfo = new ApiInfo(
                "API Pipo Saúde",
                "API feita para o exercício",
                "2.0.0",
                "",
                new Contact("Gabriel Reis",
                        "https://www.linkedin.com/in/gabriel-augusto-reis-da-silva-2bb549193/",
                        "gabriel.reis1945@gmail.com"),
                "Apache License Version 2.0",
                "https://www.apache.org/licenses/LICENSE-2.0", new ArrayList<VendorExtension>());
        return apiInfo;
    }

}
