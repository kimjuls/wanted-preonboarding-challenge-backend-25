package com.wanted.clone.oneport.payments.presentation.port.in;

import com.wanted.clone.oneport.payments.presentation.web.request.payment.ReqPaymentApprove;

import java.io.IOException;

public interface PaymentFullfillUseCase {
    String paymentApproved(ReqPaymentApprove requestMessage) throws IOException;
}
