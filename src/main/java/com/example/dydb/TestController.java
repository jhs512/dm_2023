package com.example.dydb;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final DynamoDBService dynamoDBService;

    @GetMapping("/test")
    public List<MyClass> findAll() {
        return dynamoDBService.getAllItems("hello");
    }
}
