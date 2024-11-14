package com.wanted.clone.oneport.payments.presentation.web.request.order;

import com.wanted.clone.oneport.core.common.IdGenerator;
import com.wanted.clone.oneport.payments.domain.entity.order.Order;
import com.wanted.clone.oneport.payments.domain.entity.order.OrderItem;
import com.wanted.clone.oneport.payments.domain.entity.order.OrderStatus;
import com.wanted.clone.oneport.payments.domain.entity.order.PurchaseOrderId;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.*;

@Getter
@AllArgsConstructor
public class ReqNewOrder {
    @Valid
    @NotNull(message = "The orderer is required.")
    private Orderer orderer;

    @Valid
    @Size(min = 1)
    private List<OrderedItem> newlyOrderedItem;

    @Getter
    @AllArgsConstructor
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

    public List<OrderItem> convert2OrderItems(Order o) {
        return newlyOrderedItem.stream()
            .map(items -> convert2OrderItem(items, o))
            .toList();
    }

    private OrderItem convert2OrderItem(OrderedItem item, Order o) {
        return OrderItem.builder()
            .order(o)
            .id(new PurchaseOrderId(o.getOrderId(), item.getItemIdx()))
            .productId(item.getProductId())
            .productName(item.getProductName())
            .price(item.getPrice())
            .quantity(item.getQuantity())
            .size("FREE")
            .state(OrderStatus.ORDER_COMPLETED)
            .build();
    }

    public Order toEntity() throws Exception {
        Order o = Order.builder()
            .orderId(IdGenerator.generateId(14))
            .items(new ArrayList<>())
            .name(this.getOrderer().getName())
            .phoneNumber(this.getOrderer().getPhoneNumber())
            .build();

        o.getItems().addAll(this.convert2OrderItems(o));
        if (Order.verifyHaveAtLeastOneItem(o.getItems())) throw new Exception("Noting Items");
        o.calculateTotalAmount();
        return o;
    }
}
