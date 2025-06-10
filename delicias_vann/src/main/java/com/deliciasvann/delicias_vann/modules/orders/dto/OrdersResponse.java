package com.deliciasvann.delicias_vann.modules.orders.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.deliciasvann.delicias_vann.shared.enums.OrderStatus;

import lombok.Data;

@Data
public class OrdersResponse {
    private UUID id;
    private UUID customerId;
    private List<OrderItemResponse> items;
    private Double totalPrice;
    private OrderStatus status;
    private LocalDateTime createdAt;
}
