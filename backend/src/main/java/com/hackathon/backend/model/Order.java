package com.hackathon.backend.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="orders")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "medicine_id")
    private Long medicineId;

    @Column(name = "total_amount")
    private Double totalAmount;

    @Column(name = "order_date")
    private LocalDateTime orderDate;
    
    private Integer quantity;
    private String status; // e.g., "PENDING", "COMPLETED"
}