package com.wanted.clone.oneport.payments.domain.entity.order;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PurchaseOrderId implements Serializable {
    @Column(name = "order_id")
    private String orderId;

    @Column(name = "item_idx")
    private int itemIdx;
}
