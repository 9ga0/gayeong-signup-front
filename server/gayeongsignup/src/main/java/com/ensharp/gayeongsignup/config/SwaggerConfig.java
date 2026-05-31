package com.ensharp.gayeongsignup.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
//        Server devServer = new Server();
//        devServer.setUrl("http://43.201.165.33:8080/"); // API 서버 설정

//        Server prodServer = new Server();
//        prodServer.setUrl("운영 URL"); // 운영서버에 따로 띄우기 위한 서버 추가 가능

        Info info = new Info()
                .title("Gayeong Signup Swagger API") // API 문서 제목
                .version("1.0.0") // API 문서 버전
                .description("En# 구가영 Signup 스터디용 Swagger API 문서입니다"); // API 문서 설명

        return new OpenAPI()
                .info(info);
//                .servers(List.of(devServer, prodServer));
    }
}