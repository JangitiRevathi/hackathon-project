package com.hackathon.backend.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class OrderResponse {
    private Long id; // Change 'orderId' to 'id'
    private String medicineName;
    private Integer quantity;
    private Double totalAmount;
    private LocalDateTime orderDate;
    private String status;
}