package com.kyrie.order.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @auther: jijin
 * @date: 2023/7/2 17:17 周日
 * @project_name: MicroService-Project
 * @version: 1.0
 * @description TODO
 */

@Configuration
@EnableSwagger2
public class SwaggerDocumentationConfig {
    ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("订单管理接口")
                .description("订单管理接口 description")
                .license("Apache 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .termsOfServiceUrl("")
                .version("1.0.0")
                .contact(new Contact("冀金梁", "http://wimoor.com", "625103471@qq.com"))
                .build();
    }

    @Bean
    public Docket customImplementation() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.kyrie.order"))
                .build()
                .apiInfo(apiInfo());
    }
}
