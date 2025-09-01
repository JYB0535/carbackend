package com.carbackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() { //cors설정 조작
        return new WebMvcConfigurer() { //저거 생성하자마자 상속?
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") //모든 api 요청에
                        .allowedOrigins("http://localhost:5173") //어떤 origins 허용할건지?
                        .allowedMethods("*") //get fetch delete 등등
                        .allowedHeaders("*"); //어떤 헤더들 허용할건지
            }
        };

    }

}
