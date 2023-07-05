package com.example.dydb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.mapper.BeanTableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.*;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.paginators.ScanIterable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class DynamoDBService {

    private final DynamoDbEnhancedClient dynamoDbEnhancedClient;

    @Autowired
    public DynamoDBService(DynamoDbClient dynamoDbClient) {
        this.dynamoDbEnhancedClient = DynamoDbEnhancedClient.builder()
                .dynamoDbClient(dynamoDbClient)
                .build();
    }

    public void putItem(String tableName, MyClass item) {
        DynamoDbTable<MyClass> table = dynamoDbEnhancedClient.table(tableName, BeanTableSchema.create(MyClass.class));
        PutItemEnhancedRequest<MyClass> putItemEnhancedRequest = PutItemEnhancedRequest.builder(MyClass.class)
                .item(item)
                .build();

        table.putItem(putItemEnhancedRequest);
    }

    public MyClass getItem(String tableName, Long id) {
        DynamoDbTable<MyClass> table = dynamoDbEnhancedClient.table(tableName, BeanTableSchema.create(MyClass.class));

        Key key = Key.builder().partitionValue(id).build();

        GetItemEnhancedRequest getItemEnhancedRequest = GetItemEnhancedRequest.builder()
                .key(key)
                .build();

        return table.getItem(getItemEnhancedRequest);
    }

    public List<MyClass> getAllItems(String tableName) {
        DynamoDbTable<MyClass> table = dynamoDbEnhancedClient.table(tableName, BeanTableSchema.create(MyClass.class));
        ScanEnhancedRequest scanEnhancedRequest = ScanEnhancedRequest.builder().build();
        PageIterable<MyClass> scan = table.scan(scanEnhancedRequest);

        return  scan.items().stream().toList();
    }
}
