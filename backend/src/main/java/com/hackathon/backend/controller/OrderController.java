package com.hackathon.backend.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.hackathon.backend.dto.OrderRequest;
import com.hackathon.backend.dto.OrderResponse;
import com.hackathon.backend.service.OrderService;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "*") // Critical for your React/UI connection
public class OrderController {

    @Autowired
    private OrderService orderService;

    // Create order using the Logic in your Service
    @PostMapping
    public OrderResponse createOrder(@RequestBody OrderRequest request) {
        return orderService.placeOrder(request);
    }

    // Get all orders (Returning the DTO, not the Entity)
    @GetMapping
    public List<OrderResponse> getAllOrders() {
        return orderService.getAllOrders();
    }

    // Note: I removed getById and delete for now because 
    // your ServiceImpl currently only has placeOrder and getAllOrders.
}