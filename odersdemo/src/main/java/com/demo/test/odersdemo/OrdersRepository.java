package com.demo.test.odersdemo;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OrdersRepository {

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    public void save(Order order) {
        dynamoDBMapper.save(order);
    }

    public Order getOrder(Integer id) {
        return dynamoDBMapper.load(Order.class, id);
    }

    public PaginatedScanList<Order> getAllOrders() {
        return dynamoDBMapper.scan(Order.class, new DynamoDBScanExpression());
    }

    public Order updateOrder(Integer id, Order order) {
        dynamoDBMapper.save(order,
                new DynamoDBSaveExpression().withExpectedEntry(
                        "id",
                        new ExpectedAttributeValue(
                                new AttributeValue().withN("" + id)
                        )
                )
        );
        return dynamoDBMapper.load(Order.class, id);
    }

    public String delOrder(Integer id) {
        Order order = dynamoDBMapper.load(Order.class, id);
        dynamoDBMapper.delete(order);
        return "Order is deleted";
    }
}
