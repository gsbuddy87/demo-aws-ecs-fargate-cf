package com.demo.test.odersdemo;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamoDBTable(tableName = "newordersapi-OrdersTable-U3QY1IUC5V69")
public class Order {
	@DynamoDBHashKey
	@DynamoDBAttribute
	private Integer id;
	@DynamoDBAttribute
	private String itemName;
	@DynamoDBAttribute
	private Integer quantity;
}
