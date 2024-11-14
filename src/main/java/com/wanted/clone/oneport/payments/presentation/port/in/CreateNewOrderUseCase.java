package com.wanted.clone.oneport.payments.presentation.port.in;

import com.wanted.clone.oneport.payments.domain.entity.order.Order;
import com.wanted.clone.oneport.payments.presentation.web.request.order.PurchaseOrder;
import com.wanted.clone.oneport.payments.presentation.web.request.order.ReqNewOrder;

public interface CreateNewOrderUseCase {
    Order createOrder(ReqNewOrder newOrder) throws Exception;
}
