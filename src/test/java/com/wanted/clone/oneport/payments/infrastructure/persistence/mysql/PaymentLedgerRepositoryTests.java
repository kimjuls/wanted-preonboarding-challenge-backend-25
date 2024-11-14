package com.wanted.clone.oneport.payments.infrastructure.persistence.mysql;

import com.wanted.clone.oneport.payments.domain.entity.payment.PaymentLedger;
import com.wanted.clone.oneport.payments.domain.entity.payment.PaymentMethod;
import com.wanted.clone.oneport.payments.domain.entity.payment.PaymentStatus;
import com.wanted.clone.oneport.payments.infrastructure.persistence.mysql.payment.JpaPaymentLedgerRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@Slf4j
public class PaymentLedgerRepositoryTests {

    @Autowired
    private JpaPaymentLedgerRepository jpaPaymentLedgerRepository;

    @Test
    public void save_true_PaymentLedger() throws Exception {
        // Given
        PaymentLedger paymentInfo = PaymentLedger.builder()
            .id(1)
            .transactionId("")
            .method(PaymentMethod.CARD)
            .paymentStatus(PaymentStatus.DONE)
            .totalAmount(3400)
            .balanceAmount(3400)
            .canceledAmount(0)
            .build();

        // When
        PaymentLedger result = jpaPaymentLedgerRepository.save(paymentInfo);

        // Then
        assertThat(result).usingRecursiveComparison()
                .isEqualTo(paymentInfo);
    }

}
