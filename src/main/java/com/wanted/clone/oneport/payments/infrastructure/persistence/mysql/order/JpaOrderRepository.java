package com.wanted.clone.oneport.payments.infrastructure.persistence.mysql.order;


import com.wanted.clone.oneport.payments.domain.entity.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JpaOrderRepository extends JpaRepository<Order, String> {
}
