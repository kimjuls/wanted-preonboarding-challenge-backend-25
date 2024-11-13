package com.wanted.clone.oneport.payments.infrastructure.persistence.mysql.payment;


import com.wanted.clone.oneport.payments.domain.entity.payment.card.CardPayment;
import com.wanted.clone.oneport.payments.infrastructure.persistence.mysql.JpaBaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaCardPaymentRepository extends JpaBaseRepository<CardPayment, String> {
}