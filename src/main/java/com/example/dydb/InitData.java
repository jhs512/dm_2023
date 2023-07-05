package com.example.dydb;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InitData {

    @Bean
    public ApplicationRunner devInit(DynamoDBService dynamoDBService) {
        return args -> {

            for(long i=100; i<=110; i++) {
                MyClass data = MyClass.builder()
                        .id(i)
                        .name("hello" + i)
                        .password("hello" + i)
                        .build();
                dynamoDBService.putItem("hello", data);
            }
        };
    }
}
