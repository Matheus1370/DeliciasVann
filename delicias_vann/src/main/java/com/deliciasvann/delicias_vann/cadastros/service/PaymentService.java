package com.deliciasvann.delicias_vann.cadastros.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deliciasvann.delicias_vann.cadastros.model.dto.PaymentRequest;
import com.deliciasvann.delicias_vann.cadastros.model.dto.PaymentResponse;
import com.deliciasvann.delicias_vann.cadastros.model.entity.OrderEntity;
import com.deliciasvann.delicias_vann.cadastros.model.entity.PaymentEntity;
import com.deliciasvann.delicias_vann.cadastros.repository.OrderRepository;
import com.deliciasvann.delicias_vann.cadastros.repository.PaymentRepository;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private OrderRepository orderRepository;

    public UUID create(PaymentRequest request) {
        PaymentEntity entity = new PaymentEntity();
        entity.setAmountPaid(request.getAmountPaid());
        entity.setPaymentDate(request.getPaymentDate());
        entity.setPaymentMethod(request.getPaymentMethod());
        entity.setStatus(request.getStatus());

        OrderEntity order = orderRepository.findById(request.getOrderId())
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

