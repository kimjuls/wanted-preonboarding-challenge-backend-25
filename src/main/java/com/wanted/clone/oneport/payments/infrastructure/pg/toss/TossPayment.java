package com.wanted.clone.oneport.payments.infrastructure.pg.toss;

import com.wanted.clone.oneport.payments.application.port.out.pg.PaymentAPIs;
import com.wanted.clone.oneport.payments.application.service.dto.PaymentApproveResponse;
import com.wanted.clone.oneport.payments.infrastructure.pg.toss.request.TossApproveMessage;
import com.wanted.clone.oneport.payments.infrastructure.pg.toss.request.TossCancelMessage;
import com.wanted.clone.oneport.payments.infrastructure.pg.toss.response.TossApproveResponseMessage;
import com.wanted.clone.oneport.payments.infrastructure.pg.toss.response.TossCancelResponseMessage;
import com.wanted.clone.oneport.payments.infrastructure.pg.toss.response.TossSettlementsResponseMessage;
import com.wanted.clone.oneport.payments.presentation.web.request.order.ReqCancelOrder;
import com.wanted.clone.oneport.payments.presentation.web.request.payment.ReqPaymentApprove;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class TossPayment implements PaymentAPIs {
    private final TossPaymentAPIs tossClient;

    @Override
    public PaymentApproveResponse requestPaymentApprove(ReqPaymentApprove requestMessage) throws IOException {
        TossApproveMessage message = TossApproveMessage.from(requestMessage);
        Response<TossApproveResponseMessage> response = tossClient.paymentFullfill(message).execute();
        if (response.isSuccessful())
            return Objects.requireNonNull(response.body())
                    .toCommonMessage();

        throw new IOException(response.message());
    }

    @Override
    public boolean isPaymentApproved(String status) {
        return "DONE".equalsIgnoreCase(status);
    }

    @Override
    public TossCancelResponseMessage requestPaymentCancel(String txId, ReqCancelOrder requestMessage) throws IOException {
        Response<TossCancelResponseMessage> response = tossClient.paymentCancel(txId, TossCancelMessage.from(requestMessage)).execute();
        if (response.isSuccessful()) {
            return response.body();
        }

        throw new IOException(response.message());
    }

    @Override
    public List<TossSettlementsResponseMessage> requestPaymentSettlement() throws IOException {
        return null;
    }
}
