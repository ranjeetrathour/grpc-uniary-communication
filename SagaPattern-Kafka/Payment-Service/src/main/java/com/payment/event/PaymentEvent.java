package com.payment.event;

import com.order.dto.OrderRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder(toBuilder = true)
public class PaymentEvent {
    @Builder.Default
    private String eventId = UUID.randomUUID().toString();
    private String eventType;
    private OrderRequestDto orderRequestDto;
}
