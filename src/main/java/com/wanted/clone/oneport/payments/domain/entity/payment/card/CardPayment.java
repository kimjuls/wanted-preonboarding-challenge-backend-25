package com.wanted.clone.oneport.payments.domain.entity.payment.card;

import com.wanted.clone.oneport.payments.domain.entity.payment.TransactionType;
import com.wanted.clone.oneport.payments.infrastructure.pg.toss.response.TossApproveResponseMessage;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "card_payment")
@Getter
@SuperBuilder
@AllArgsConstructor
public class CardPayment extends TransactionType {
    @Id
    @Column(name = "payment_key")
    private String paymentKey; // example) tgen_20240605132741Jtkz1

    @Column(name = "card_number")
    private String cardNumber;

    @Column(name = "approve_no")
    private String approveNo;

    @Column(name = "acquire_status")
    @Convert(converter = AcquireStatusConverter.class)
    private AcquireStatus acquireStatus;

    @Column(name = "issuer_code")
    private String issuer_code;

    @Column(name = "acquirer_code")
    private String acquirerCode;

    @Column(name = "acquirer_status")
    private String acquirerStatus;

    protected CardPayment() {
    }

    public static CardPayment from(TossApproveResponseMessage response) {
        return CardPayment.builder()
                .paymentKey(response.getPaymentKey())
                .cardNumber(response.getCard().getNumber())
                .approveNo(response.getCard().getApproveNo())
                .acquireStatus(AcquireStatus.valueOf(response.getCard().getAcquireStatus()))
                .acquirerCode(response.getCard().getAcquirerCode())
                .acquirerStatus(response.getCard().getAcquireStatus())
                .build();
    }
}
