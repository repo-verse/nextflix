package com.apistone.nextflix.config;

import com.apistone.nextflix.constant.StringConstant;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

/**
 * Project: nextflix
 * Package: com.apistone.nextflix.bean
 * <p>
 * Created by: Rahul Kumar Maurya
 * Date: 1/21/2023
 * Time: 12:26 PM
 * <p>
 * Use: Open API Specification using swagger for API documentation.
 * <a href="http://localhost:8080/api/v1/swagger-ui/index.html">API Documentation</a>
 */
@EnableSwagger2
@EnableWebMvc
@Configuration
public class SwaggerConfig {
    private ApiInfo apiInfo() {
        return new ApiInfo(
                StringConstant.API_INFO_TITLE,
                StringConstant.API_INFO_DESCRIPTION,
                StringConstant.API_INFO_VERSION,
                StringConstant.API_INFO_TERMS_OF_SERVICES,
                new Contact(
                        StringConstant.API_INFO_CONTACT_NAME,
                        StringConstant.API_INFO_CONTACT_URL,
                        StringConstant.API_INFO_CONTACT_EMAIL
                ),
                StringConstant.API_INFO_LICENSE,
                StringConstant.API_INFO_LICENSE_URL,
                Collections.emptyList()
        );
    }
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }
}
