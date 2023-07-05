package com.example.dydb;

import lombok.*;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@DynamoDbBean
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MyClass {

    private Long id;
    private String name;
    private String password;

    @DynamoDbPartitionKey
    public Long getId() {
        return id;
    }
}
