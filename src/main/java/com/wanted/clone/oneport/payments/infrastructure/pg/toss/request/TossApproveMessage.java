package com.wanted.clone.oneport.payments.infrastructure.pg.toss.request;

import com.wanted.clone.oneport.payments.infrastructure.pg.CommonApproveMessage;
import com.wanted.clone.oneport.payments.presentation.web.request.payment.ReqPaymentApprove;
import lombok.*;

@Getter
@Builder
@RequiredArgsConstructor
public class TossApproveMessage extends CommonApproveMessage {
    private final String paymentKey;
    private final String orderId;
    private final int amount;

//    @Builder
//    private TossApproveMessage(String paymentKey, String orderId, int totalAmount) {
//        this.paymentKey = paymentKey;
//        this.orderId = orderId;
//        this.totalAmount = totalAmount;
//    }

    @Override
    public String toString() {
        return "PaymentApproveMessage [payment_key=" + paymentKey +
                ", order_id=" + orderId +
                ", total_amount=" + amount +
                "]";
    }

    public static TossApproveMessage from(ReqPaymentApprove requestMessage) {
        return TossApproveMessage.builder()
                .paymentKey(requestMessage.getPaymentKey())
                .orderId(requestMessage.getOrderId())
                .amount(requestMessage.getTotalAmount())
                .build();
    }
}
