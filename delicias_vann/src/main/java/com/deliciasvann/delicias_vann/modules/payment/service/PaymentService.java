package com.deliciasvann.delicias_vann.modules.payment.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deliciasvann.delicias_vann.modules.orders.entities.OrdersEntity;
import com.deliciasvann.delicias_vann.modules.orders.repositories.OrdersRepository;
import com.deliciasvann.delicias_vann.modules.payment.PaymentEntity;
import com.deliciasvann.delicias_vann.modules.payment.PaymentRepository;
import com.deliciasvann.delicias_vann.modules.payment.dto.PaymentRequest;
import com.deliciasvann.delicias_vann.modules.payment.dto.PaymentResponse;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private OrdersRepository ordersRepository;

    public UUID create(PaymentRequest request) {
        PaymentEntity entity = new PaymentEntity();
        entity.setAmountPaid(request.getAmountPaid());
        entity.setPaymentDate(request.getPaymentDate());
        entity.setPaymentMethod(request.getPaymentMethod());
        entity.setStatus(request.getStatus());

        OrdersEntity order = ordersRepository.findById(request.getOrderId())
            .orElseThrow(() -> new RuntimeException("Order not found"));
        entity.setOrder(order);

        return paymentRepository.save(entity).getId();
    }

    public UUID update(UUID id, PaymentRequest request) {
        PaymentEntity entity = paymentRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Payment not found"));

        entity.setAmountPaid(request.getAmountPaid());
        entity.setPaymentDate(request.getPaymentDate());
        entity.setPaymentMethod(request.getPaymentMethod());
        entity.setStatus(request.getStatus());

        return paymentRepository.save(entity).getId();
    }

    public PaymentResponse findById(UUID id) {
        PaymentEntity entity = paymentRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Payment not found"));

        PaymentResponse response = new PaymentResponse();
        response.setId(entity.getId());
        response.setOrderId(entity.getOrder().getId());
        response.setAmountPaid(entity.getAmountPaid());
        response.setPaymentMethod(entity.getPaymentMethod());
        response.setStatus(entity.getStatus());
        response.setPaymentDate(entity.getPaymentDate());

        return response;
    }

    public List<PaymentResponse> findAll() {
        List<PaymentEntity> payments = paymentRepository.findAll();
        List<PaymentResponse> responses = new ArrayList<>();

        for (PaymentEntity entity : payments) {
            PaymentResponse response = new PaymentResponse();
            response.setId(entity.getId());
            response.setOrderId(entity.getOrder().getId());
            response.setAmountPaid(entity.getAmountPaid());
            response.setPaymentMethod(entity.getPaymentMethod());
            response.setStatus(entity.getStatus());
            response.setPaymentDate(entity.getPaymentDate());
            responses.add(response);
        }

        return responses;
    }

    public void delete(UUID id) {
        paymentRepository.deleteById(id);
    }
}

