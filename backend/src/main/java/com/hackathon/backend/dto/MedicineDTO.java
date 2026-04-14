package com.hackathon.backend.dto;

import lombok.Data;

@Data
public class MedicineDTO {
    private Long id;
    private String name;
    private String category;
    private Double price;
    private boolean prescriptionRequired;
    private Integer stockQuantity;
}