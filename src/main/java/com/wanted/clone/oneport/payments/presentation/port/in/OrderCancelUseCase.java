package com.wanted.clone.oneport.payments.presentation.port.in;

import com.wanted.clone.oneport.payments.presentation.web.request.order.ReqCancelOrder;

public interface OrderCancelUseCase {
    boolean orderCancel(ReqCancelOrder reqCancelOrder) throws Exception;
}
