package com.baraq.ecomm.order.persistence.repo;

import com.baraq.ecomm.order.persistence.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
