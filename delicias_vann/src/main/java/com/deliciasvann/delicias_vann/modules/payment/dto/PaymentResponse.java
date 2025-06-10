package com.deliciasvann.delicias_vann.modules.payment.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.deliciasvann.delicias_vann.shared.enums.PaymentMethod;
import com.deliciasvann.delicias_vann.shared.enums.PaymentStatus;

import lombok.Data;

@Data
public class PaymentResponse {
    private UUID id;
    private UUID orderId;
    private Double amountPaid;
    private PaymentMethod paymentMethod;
    private PaymentStatus status;
    private LocalDateTime paymentDate;
}
