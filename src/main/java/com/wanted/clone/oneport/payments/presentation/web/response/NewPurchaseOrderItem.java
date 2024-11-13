package com.wanted.clone.oneport.payments.presentation.web.response;

import com.wanted.clone.oneport.payments.domain.entity.order.OrderItem;
import com.wanted.clone.oneport.payments.domain.entity.order.OrderStatus;
import lombok.*;

import java.util.*;

@Data
@RequiredArgsConstructor
@Builder
public class NewPurchaseOrderItem {

    private final String orderId;

    private final int itemIdx;

    private final UUID productId;

    private final String productName;

    private final int price;

    private final String size;

    private final int amount;

    private final int quantity;

    private final OrderStatus itemStatus;

    public static List<NewPurchaseOrderItem> from(List<OrderItem> orderItems) {
        return orderItems.stream()
                .map(orderItem ->
                        NewPurchaseOrderItem.builder()
                                .orderId(orderItem.getId().getOrderId())
                                .itemIdx(orderItem.getId().getItemIdx())
                                .productName(orderItem.getProductName())
                                .price(orderItem.getPrice())
                                .size(orderItem.getSize())
                                .amount(orderItem.getAmount())
                                .quantity(orderItem.getQuantity())
                                .itemStatus(orderItem.getState())
                                .build())
                .toList();
    }

}