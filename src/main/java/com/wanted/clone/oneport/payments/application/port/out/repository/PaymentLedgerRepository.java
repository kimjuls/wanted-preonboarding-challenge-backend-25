package com.wanted.clone.oneport.payments.application.port.out.repository;


import com.wanted.clone.oneport.payments.domain.entity.payment.PaymentLedger;

import java.util.List;

public interface PaymentLedgerRepository {
    List<PaymentLedger> findAllByTransactionId(String paymentKey);
    PaymentLedger findOneByTransactionIdDesc(String paymentKey);
    void save(PaymentLedger paymentLedgerInfo);
    void bulkInsert(List<PaymentLedger> paymentLedgerHistories);
}
