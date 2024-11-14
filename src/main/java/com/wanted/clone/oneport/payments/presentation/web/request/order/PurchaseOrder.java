package com.wanted.clone.oneport.payments.presentation.web.request.order;

import com.wanted.clone.oneport.core.common.IdGenerator;
import com.wanted.clone.oneport.payments.domain.entity.order.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseOrder {

    @Valid
    @NotNull(message = "The orderer is required.")
    private Orderer orderer;

    @Valid
    @Size(min = 1)
    private List<PurchaseOrderItem> newlyOrderedItem;

}