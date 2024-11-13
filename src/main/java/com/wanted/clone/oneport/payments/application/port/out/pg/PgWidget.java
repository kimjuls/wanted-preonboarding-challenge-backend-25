package com.wanted.clone.oneport.payments.application.port.out.pg;

import com.wanted.clone.oneport.payments.infrastructure.pg.toss.TossWidget;

public interface PgWidget {
    String checkout();

    String success();

    String fail();

    static PgWidget of(String pgCorpName) {
        return switch (pgCorpName.toLowerCase()) {
            case "toss" -> new TossWidget();
            default -> throw new IllegalArgumentException("Invalid pgCorp name: " + pgCorpName);
        };
    }
}
