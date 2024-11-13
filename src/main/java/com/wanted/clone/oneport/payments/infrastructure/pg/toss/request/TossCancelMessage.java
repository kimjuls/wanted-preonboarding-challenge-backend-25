package com.wanted.clone.oneport.payments.infrastructure.pg.toss.request;

import com.wanted.clone.oneport.payments.presentation.web.request.order.ReqCancelOrder;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TossCancelMessage {
    private final String cancelReason;
    private final int cancelAmount;

    public static TossCancelMessage from(ReqCancelOrder requestMessage) {
        return TossCancelMessage.builder()
                .cancelReason(requestMessage.getCancelReason())
                .cancelAmount(requestMessage.getCancellationAmount())
                .build();
    }
}
