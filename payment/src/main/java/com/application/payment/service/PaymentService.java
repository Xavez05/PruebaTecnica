package com.application.payment.service;

import com.application.payment.dto.PaymentDTO;

public interface PaymentService {

    PaymentDTO processPayment(PaymentDTO paymentDTO);
    PaymentDTO getPaymentByOrderId(Long orderId);
}
