package com.wanted.clone.oneport.payments.presentation.web.request.order;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.*;

@Getter
public class ReqNewOrder {
    @Valid
    @NotNull(message = "The orderer is required.")
    private Orderer orderer;

    @Valid
    @Size(min = 1)
    private List<OrderedItem> newlyOrderedItem;

    @Getter
    public static class OrderedItem {
        @Min(1)
        private int itemIdx;

        private UUID productId;

        @NotBlank
        private String productName;

        private int price;    // 가격

        @Min(1)
        private int quantity; // 수량

        private int amounts;  // price * quantity
    }
}
