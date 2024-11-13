package com.wanted.clone.oneport.payments.presentation.port.in;


import com.wanted.clone.oneport.payments.domain.entity.order.Order;

import java.util.UUID;

public interface GetOrderInfoUseCase {
    Order getOrderInfo(String orderId);
}
