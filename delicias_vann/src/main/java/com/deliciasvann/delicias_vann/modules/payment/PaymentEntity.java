package com.deliciasvann.delicias_vann.modules.payment;

import java.time.LocalDateTime;
import java.util.UUID;

import com.deliciasvann.delicias_vann.modules.orders.entities.OrdersEntity;
import com.deliciasvann.delicias_vann.shared.enums.PaymentMethod;
import com.deliciasvann.delicias_vann.shared.enums.PaymentStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity(name = "payment")
public class PaymentEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "order_id", nullable = false)
    private OrdersEntity order;

    private Double amountPaid;
    
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
    
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;
    
    private LocalDateTime paymentDate;

    public boolean isPaymentCompleted() {
        return this.status == PaymentStatus.CONFIRMED;
    }
}
