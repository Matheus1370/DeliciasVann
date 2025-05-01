package com.deliciasvann.delicias_vann.cadastros.model.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.deliciasvann.delicias_vann.cadastros.model.enums.OrderStatus;

import lombok.Data;

@Data
public class OrderResponse {
    private UUID id;
    private UUID customerId;
    private List<OrderItemResponse> items;
    private Double totalPrice;
    private OrderStatus status;
    private LocalDateTime orderDate;
}
