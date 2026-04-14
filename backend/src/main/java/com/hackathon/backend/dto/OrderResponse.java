package com.hackathon.backend.dto;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class OrderResponse {
    private Long orderId;
    private String medicineName;
    private Integer quantity;
    private Double totalAmount;
    private LocalDateTime orderDate;
    private String status;
}
