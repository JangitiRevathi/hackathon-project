package com.hackathon.backend.service;

import com.hackathon.backend.dto.OrderRequest;
import com.hackathon.backend.dto.OrderResponse;
import java.util.List;

public interface OrderService {
    // 1. The "Brain" - handles stock and checkout
    OrderResponse placeOrder(OrderRequest request);

    // 2. The "Dashboard" - shows all orders for the pharmacist
    List<OrderResponse> getAllOrders();

    // 3. The "Management" - for canceling/deleting
    void deleteOrder(Long id);
}