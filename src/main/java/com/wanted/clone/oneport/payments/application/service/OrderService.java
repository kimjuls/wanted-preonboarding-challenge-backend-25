package com.wanted.clone.oneport.payments.application.service;

import com.wanted.clone.oneport.payments.application.port.out.repository.OrderRepository;
import com.wanted.clone.oneport.payments.domain.entity.order.Order;
import com.wanted.clone.oneport.payments.presentation.port.in.CreateNewOrderUseCase;
import com.wanted.clone.oneport.payments.presentation.port.in.GetOrderInfoUseCase;
import com.wanted.clone.oneport.payments.presentation.web.request.order.PurchaseOrder;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService implements CreateNewOrderUseCase, GetOrderInfoUseCase {
    private final OrderRepository orderRepository;

    @Transactional
    @Override
    public Order createOrder(PurchaseOrder newOrder) throws Exception {
        return orderRepository.save(newOrder.toEntity());
    }

    @Override
    public Order getOrderInfo(String orderId) {
        return orderRepository.findById(orderId);
    }
}
