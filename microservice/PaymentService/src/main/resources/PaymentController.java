package com.payment.controller;

import com.payment.dto.request.PaymentRequest;
import com.payment.dto.request.response.PaymentResponse;
import com.payment.service.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/save")
    public ResponseEntity<String> savePayment(@RequestBody PaymentRequest paymentRequest) {
        try {
            paymentService.savePayment(paymentRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body("Payment processed successfully!");
        } catch (Exception e) {
            // Return failure response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Payment processing failed: " + e.getMessage());
        }
    }

    @GetMapping("/all-payments/{userId}")
    public ResponseEntity<List<PaymentResponse>> getAllPaymentByUserId(@PathVariable Long userId){
        try{
            final  var response = paymentService.findAllPaymentsByUserId(userId);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
