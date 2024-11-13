package com.wanted.clone.oneport.payments.infrastructure.pg.toss.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.wanted.clone.oneport.payments.domain.entity.payment.PaymentMethod;
import com.wanted.clone.oneport.payments.domain.entity.payment.PaymentStatus;
import com.wanted.clone.oneport.payments.domain.entity.settlements.PaymentSettlements;
import com.wanted.clone.oneport.payments.infrastructure.pg.toss.response.payment.Cancel;
import com.wanted.clone.oneport.payments.infrastructure.pg.toss.response.payment.method.Card;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@Slf4j
public class TossSettlementsResponseMessage {
    private String orderId;
    private String paymentKey;
    private String method;
    @JsonProperty("amount")
    private int totalAmount;
    private Card card;
    private Cancel cancel;
    private int payOutAmount; // 지급 금액입니다. 결제 금액 amount에서 수수료인 fee를 제외한 금액입니다.
    private String soldDate;
    private String paidOutDate;

    private PaymentStatus converterToEntityAttribute() {
        switch (card.getAcquireStatus()) {
            case "READY":
            case "REQUESTED":
                return PaymentStatus.valueOf("SETTLEMENTS_REQUESTED");
            case "COMPLETED":
                return PaymentStatus.valueOf("SETTLEMENTS_COMPLETED");
            case "CANCEL_REQUESTED":
            case "CANCELED":
                return PaymentStatus.valueOf("SETTLEMENTS_CANCELED");
            default:
                return PaymentStatus.valueOf("SETTLEMENTS_REQUESTED");
        }

    }

    public PaymentSettlements toEntity() {
        return PaymentSettlements.builder()
                .paymentKey(paymentKey)
                .paymentStatus(this.converterToEntityAttribute())
                .method(PaymentMethod.fromMethodName(method))
                .totalAmount(totalAmount)
                .canceledAmount(cancel == null ? 0 : cancel.getCancelAmount())
                .payOutAmount(payOutAmount)
                .soldDate(Date.valueOf(soldDate))
                .paidOutDate(Date.valueOf(paidOutDate))
                .build();
    }

}
