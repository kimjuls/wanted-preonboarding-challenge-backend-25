package com.wanted.clone.oneport.payments.application.port.out.repository;

import com.wanted.clone.oneport.payments.domain.entity.order.Order;

import java.util.Optional;
import java.util.UUID;

public interface OrderRepository {
    Order findById(String id);
    Order save(Order newOrder);
}
