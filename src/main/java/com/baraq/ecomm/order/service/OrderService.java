package com.baraq.ecomm.order.service;

import com.baraq.ecomm.order.dto.RequestDTO.OrderRequestDTO;
import com.baraq.ecomm.shared.dto.OrderDTO;

public interface OrderService {
    public OrderDTO createOrder(OrderRequestDTO orderRequestDTO);
}
