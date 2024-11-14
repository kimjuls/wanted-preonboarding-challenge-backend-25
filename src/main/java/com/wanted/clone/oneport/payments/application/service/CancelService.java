package com.wanted.clone.oneport.payments.application.service;

import com.wanted.clone.oneport.payments.application.port.out.pg.PaymentAPIs;
import com.wanted.clone.oneport.payments.application.port.out.repository.PaymentLedgerRepository;
import com.wanted.clone.oneport.payments.domain.entity.order.Order;
import com.wanted.clone.oneport.payments.domain.entity.payment.PaymentLedger;
import com.wanted.clone.oneport.payments.infrastructure.pg.toss.response.TossCancelResponseMessage;
import com.wanted.clone.oneport.payments.presentation.port.in.OrderCancelUseCase;
import com.wanted.clone.oneport.payments.presentation.web.request.order.ReqCancelOrder;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CancelService implements OrderCancelUseCase {
    private final OrderService orderService;
    private final PaymentService paymentService;
    private final PaymentLedgerRepository paymentLedgerRepository;

    @Transactional
    @Override
    public boolean orderCancel(ReqCancelOrder reqCancelOrder) throws Exception {
        String paymentKey = reqCancelOrder.getPaymentKey();
        int cancellationAmount = reqCancelOrder.getCancellationAmount();
        Order wantedCancelOrder = orderService.getOrderInfo(reqCancelOrder.getOrderId());
        PaymentLedger paymentInfo = paymentService.getLatestPaymentInfoOnlyOne(paymentKey);
        PaymentAPIs paymentAPIs = paymentService.selectPgAPI(paymentInfo.getPgCorpName());

        if (wantedCancelOrder.isNotOrderStatusPurchaseDecision() &&
                paymentInfo.isCancellableAmountGreaterThan(cancellationAmount)) {
            if (reqCancelOrder.hasItemIdx())
                wantedCancelOrder.orderCancel(reqCancelOrder.getItemIdxs());
            else
                wantedCancelOrder.orderAllCancel();
            TossCancelResponseMessage response = paymentAPIs.requestPaymentCancel(paymentKey, reqCancelOrder);
            paymentLedgerRepository.save(response.toEntity());
            return true;
        }

        throw new Exception("Not Enough CancellationAmount");
    }
}
