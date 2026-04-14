package com.hackathon.backend.service.impl;

import com.hackathon.backend.dto.OrderRequest;
import com.hackathon.backend.dto.OrderResponse;
import com.hackathon.backend.exception.ResourceNotFoundException;
import com.hackathon.backend.model.Medicine;
import com.hackathon.backend.model.Order;
import com.hackathon.backend.repository.MedicineRepository;
import com.hackathon.backend.repository.OrderRepository;
import com.hackathon.backend.service.OrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private MedicineRepository medicineRepository;

    @Override
    @Transactional // Critical: This ensures stock isn't deducted if the order fails to save
    public OrderResponse placeOrder(OrderRequest request) {
        // 1. Validate Medicine Existence
        Medicine medicine = medicineRepository.findById(request.getMedicineId())
                .orElseThrow(() -> new ResourceNotFoundException("Medicine not found with id: " + request.getMedicineId()));

        // 2. Business Logic: Check Stock Availability
        if (medicine.getStockQuantity() < request.getQuantity()) {
            throw new RuntimeException("Insufficient stock for medicine: " + medicine.getName());
        }

        // 3. Business Logic: Update Stock Quantity
        medicine.setStockQuantity(medicine.getStockQuantity() - request.getQuantity());
        medicineRepository.save(medicine);

        // 4. Create and Save Order
        Order order = new Order();
        order.setMedicineId(medicine.getId());
        order.setQuantity(request.getQuantity());
        order.setTotalAmount(medicine.getPrice() * request.getQuantity());
        order.setOrderDate(LocalDateTime.now());
        order.setStatus("COMPLETED");

        Order savedOrder = orderRepository.save(order);

        // 5. Convert to Response DTO
        return convertToResponseDTO(savedOrder, medicine.getName());
    }

    @Override
    public List<OrderResponse> getAllOrders() {
        return orderRepository.findAll().stream().map(order -> {
            String medName = medicineRepository.findById(order.getMedicineId())
                    .map(Medicine::getName)
                    .orElse("Unknown Medicine");
            return convertToResponseDTO(order, medName);
        }).collect(Collectors.toList());
    }

    private OrderResponse convertToResponseDTO(Order order, String medicineName) {
        OrderResponse response = new OrderResponse();
        BeanUtils.copyProperties(order, response);
        response.setMedicineName(medicineName);
        return response;
    }
}