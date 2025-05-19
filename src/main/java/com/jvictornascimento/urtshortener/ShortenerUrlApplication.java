package com.jvictornascimento.urtshortener;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@OpenAPIDefinition(info = @Info(title = "Url shortener",version = "0.0.1",description = "Api to url shortener!"))
public class ShortenerUrlApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShortenerUrlApplication.class, args);
    }

}
