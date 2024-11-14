package com.wanted.clone.oneport.payments.application.port.out.repository;


import com.wanted.clone.oneport.payments.domain.entity.payment.TransactionType;

public interface TransactionTypeRepository {
    TransactionType findById(String paymentKey);
    void save(TransactionType paymentDetailInfo);
}
