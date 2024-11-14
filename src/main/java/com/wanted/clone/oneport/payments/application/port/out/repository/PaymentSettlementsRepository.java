package com.wanted.clone.oneport.payments.application.port.out.repository;


import com.wanted.clone.oneport.payments.domain.entity.settlements.PaymentSettlements;

import java.util.List;

public interface PaymentSettlementsRepository {
    PaymentSettlements findById(String paymentKey);
    void bulkInsert(List<PaymentSettlements> paymentSettlements);
}
