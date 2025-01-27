package com.zxb.backEnd.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        Contact contact = new Contact();
        contact.setName("zxb");
        return new OpenAPI()
                // 增加swagger授权请求头配置
                .info(new Info()
                        .title("vue_chart 后台服务API接口文档")
                        .version("1.0")
                        .contact(contact)
                        .description("Knife4j集成springdoc-openapi示例")
                        .license(new License().name("Apache 2.0")
                                .url("http://www.apache.org/licenses/LICENSE-2.0.html")));
    }
}