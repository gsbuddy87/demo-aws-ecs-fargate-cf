package com.demo.test.odersdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
public class OrdersController {

	@Autowired
	OrdersRepository ordersRepository;

	@GetMapping(path = "/apis/orders/{id}")
	public Order getOrderDetails(@PathVariable Integer id) {
		return ordersRepository.getOrder(id);
	}

	@GetMapping(path = "/apis/orders")
	public List<Order> getAllOrders() {
		return ordersRepository.getAllOrders();
	}

	@PostMapping(path = "/apis/orders")
	public ResponseEntity<Order> addOrder(@RequestBody Order order) {
		ordersRepository.save(order);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(order.getId()).toUri();
		return ResponseEntity.created(location).build();
	}

	@PutMapping (path = "/apis/updateOrders")
	public ResponseEntity<Order> updateOrders(@RequestBody List<Order> orders) {
		orders.stream().forEach(order -> ordersRepository.updateOrder(order.getId(),order));
		return ResponseEntity.accepted().build();
	}

	@DeleteMapping (path = "/apis/deleteorder/{id}")
	public ResponseEntity<Order> deleteOrder(@PathVariable Integer id) {
		ordersRepository.delOrder(id);
		return ResponseEntity.ok().build();
	}
}
