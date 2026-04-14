package com.hackathon.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "medicines")
public class Medicine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    
    @Column(name = "category_id") // Maps Java field to MySQL column
    private Integer categoryId;

    private String dosage;
    private String packaging;
    private Double price;
    
    @Column(name = "stock_quantity") // Matches your screenshot
    private Integer stockQuantity;
    
    // Add this if you still want to handle prescriptions
    private boolean prescriptionRequired;
}