package com.deliciasvann.delicias_vann.cadastros.model.dto;

import java.util.List;
import java.util.UUID;

import lombok.Data;

@Data
public class OrderRequest {
    private UUID customerId;
    private List<OrderItemRequest> items;
}
