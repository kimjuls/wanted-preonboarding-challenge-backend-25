package com.wanted.clone.oneport.payments.presentation.web.request.payment;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.math.BigInteger;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PaymentRequestMessage {
    private String pgCorpName;
    private long siteCode;
    private UUID orderId;
    private String userId;
    private String ordererName;
    private String ordererPhoneNumber;
    private String orderedProductName;
    private BigInteger totalAmount;
}
