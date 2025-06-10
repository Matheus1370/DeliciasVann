package com.deliciasvann.delicias_vann.modules.orders.dto;

import java.util.List;
import java.util.UUID;

import lombok.Data;

@Data
public class OrdersRequest {
    private UUID customerId;
    private List<OrderItemRequest> items;
}
