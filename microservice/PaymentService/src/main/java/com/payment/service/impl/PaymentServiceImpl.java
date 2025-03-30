package com.payment.service.impl;

import com.payment.dto.request.PaymentRequest;
import com.payment.dto.request.response.PaymentResponse;
import com.payment.entity.Payment;
import com.payment.mapper.PaymentMapper;
import com.payment.repository.PaymentRepository;
import com.payment.service.PaymentService;
import com.payment.util.LoggerUtil;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private static Logger log = LoggerUtil.getLogger(PaymentMapper.class);
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;

    @Override
    public void savePayment(PaymentRequest paymentRequest){
        log.info("payment request from userId {} ", paymentRequest.getUserId());
        Payment payment =  paymentMapper.toPaymentRequest(paymentRequest);
        payment.setPaymentStatus("SUCCESS");
        payment.setPaymentDate(LocalDateTime.now());
        try {
            paymentRepository.save(payment);
            log.info("payment saved successful ");
        }catch (Exception e){
            payment.setPaymentStatus("FAILED");
            paymentRepository.save(payment);
            log.error("payment failed");
        }finally {

        }
    }

    public List<PaymentResponse> findAllPaymentsByUserId(Long userId){
        log.info("request send by user with user id {}", userId);
        try {
            List<Payment> responses = paymentRepository.findAllPaymentByUserId(userId);
            List<PaymentResponse> paymentResponses = paymentMapper.toPayments(responses);
            return paymentResponses;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("jhsagjagjs");
        }
    }

}

