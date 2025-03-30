package com.payment.service;

import com.payment.dto.request.PaymentRequest;
import com.payment.dto.request.response.PaymentResponse;

import java.util.List;

public interface PaymentService {
    void savePayment(PaymentRequest paymentRequest);
    List<PaymentResponse> findAllPaymentsByUserId(Long userId);
}
