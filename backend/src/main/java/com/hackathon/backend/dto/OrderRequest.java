package com.hackathon.backend.dto;

import lombok.Data;

@Data
public class OrderRequest {
    private Long medicineId;
    private Integer quantity;
}