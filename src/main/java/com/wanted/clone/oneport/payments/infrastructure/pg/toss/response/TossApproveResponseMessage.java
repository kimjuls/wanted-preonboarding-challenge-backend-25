package com.wanted.clone.oneport.payments.infrastructure.pg.toss.response;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.wanted.clone.oneport.payments.application.service.dto.PaymentApproveResponse;
import com.wanted.clone.oneport.payments.domain.entity.payment.PaymentLedger;
import com.wanted.clone.oneport.payments.domain.entity.payment.PaymentMethod;
import com.wanted.clone.oneport.payments.domain.entity.payment.PaymentStatus;
import com.wanted.clone.oneport.payments.infrastructure.pg.toss.response.payment.TossCommonResponseMessage;
import com.wanted.clone.oneport.payments.infrastructure.pg.toss.response.payment.method.Card;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TossApproveResponseMessage extends TossCommonResponseMessage {
    private String orderName;
    private Card card;
    private String lastTransactionKey;
    private int suppliedAmount; // 공급 가액
    private int vat;
    private String requestedAt; // 2024-06-18T15:13:15+09:00
    private String approvedAt;

    public PaymentApproveResponse toCommonMessage() {
        return PaymentApproveResponse.builder()
                .transactionId(this.getPaymentKey())
                .method(PaymentMethod.fromMethodName(this.getMethod()))
                .status(PaymentStatus.valueOf(this.getStatus()))
                .totalAmount(this.getTotalAmount())
                .balanceAmount(this.getBalanceAmount())
                .build();
    }
}
