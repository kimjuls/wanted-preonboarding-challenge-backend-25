package com.wanted.clone.oneport.payments.application.port.out.pg;

import com.wanted.clone.oneport.payments.application.service.dto.PaymentApproveResponse;
import com.wanted.clone.oneport.payments.domain.entity.payment.PaymentLedger;
import com.wanted.clone.oneport.payments.infrastructure.pg.toss.response.TossCancelResponseMessage;
import com.wanted.clone.oneport.payments.infrastructure.pg.toss.response.TossSettlementsResponseMessage;
import com.wanted.clone.oneport.payments.presentation.web.request.order.ReqCancelOrder;
import com.wanted.clone.oneport.payments.presentation.web.request.payment.ReqPaymentApprove;

import java.io.IOException;
import java.util.List;

public interface PaymentAPIs {
    PaymentApproveResponse requestPaymentApprove(ReqPaymentApprove requestMessage) throws IOException;

    boolean isPaymentApproved(String status);

    TossCancelResponseMessage requestPaymentCancel(String txId, ReqCancelOrder requestMessage) throws IOException;

    List<TossSettlementsResponseMessage> requestPaymentSettlement() throws IOException;
}
