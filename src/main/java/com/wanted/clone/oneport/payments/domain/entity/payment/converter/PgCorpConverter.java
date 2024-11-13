package com.wanted.clone.oneport.payments.domain.entity.payment.converter;

import com.wanted.clone.oneport.payments.presentation.web.request.payment.PgCorp;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class PgCorpConverter implements AttributeConverter<PgCorp, Integer> {
    @Override
    public Integer convertToDatabaseColumn(PgCorp pgCorp) {
        return pgCorp.getCode();
    }

    @Override
    public PgCorp convertToEntityAttribute(Integer code) {
        return PgCorp.valueOfCode(code);
    }
}
