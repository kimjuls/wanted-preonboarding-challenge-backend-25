package com.wanted.clone.oneport.payments.domain.entity.payment.card;

import jakarta.persistence.AttributeConverter;

public class AcquireStatusConverter implements AttributeConverter<AcquireStatus, String> {
    @Override
    public String convertToDatabaseColumn(AcquireStatus acquireStatus) {
        return acquireStatus.name();
    }

    @Override
    public AcquireStatus convertToEntityAttribute(String s) {
        return AcquireStatus.valueOf(s);
    }
}
