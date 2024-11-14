package com.wanted.clone.oneport.payments.application.service.dto;

import com.wanted.clone.oneport.payments.domain.entity.payment.PaymentLedger;
import com.wanted.clone.oneport.payments.domain.entity.payment.PaymentMethod;
import com.wanted.clone.oneport.payments.domain.entity.payment.PaymentStatus;
import com.wanted.clone.oneport.payments.infrastructure.pg.toss.response.payment.method.Card;
import com.wanted.clone.oneport.payments.presentation.web.request.payment.PgCorp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class PaymentApproveResponse {
    private String transactionId;
    private PaymentMethod method;
    private PaymentStatus status;
    private int totalAmount;
    private int balanceAmount;
    private String orderId;
    private String orderName;
    private Card card;
    private int suppliedAmount; // 공급 가액
    private int vat;
    private String requestedAt; // 2024-06-18T15:13:15+09:00
    private String approvedAt;

    public PaymentLedger toEntity(PgCorp corp) {
        return PaymentLedger.builder()
            .transactionId(this.transactionId)
            .method(this.getMethod())
            .paymentStatus(this.getStatus())
            .totalAmount(this.getTotalAmount())
            .balanceAmount(this.getBalanceAmount())
            .canceledAmount(0)
            .pgCorpName(corp)
            .build();
    }

}
