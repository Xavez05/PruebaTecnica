package com.application.payment.service.impl;

import com.application.payment.dto.PaymentDTO;
import com.application.payment.model.Payment;
import com.application.payment.repository.PaymentRepository;
import com.application.payment.service.PaymentService;
import com.application.payment.utils.PaymentMapper;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;

    public PaymentServiceImpl(PaymentRepository paymentRepository, PaymentMapper paymentMapper) {
        this.paymentRepository = paymentRepository;
        this.paymentMapper = paymentMapper;
    }

    @Override
    public PaymentDTO processPayment(PaymentDTO paymentDTO) {
        Payment payment = paymentMapper.toEntity(paymentDTO);

        if (new Random().nextBoolean()) {
            payment.setPaymentStatus("APPROVED");
        }else {
            payment.setPaymentStatus("REJECTED");
        }

        payment  = paymentRepository.save(payment);
        return paymentMapper.toDTO(payment);
    }

    @Override
    public PaymentDTO getPaymentByOrderId(Long orderId) {
        Payment payment = paymentRepository.findById(orderId).orElse(null);
        return payment != null ? paymentMapper.toDTO(payment) : null;
    }
}
