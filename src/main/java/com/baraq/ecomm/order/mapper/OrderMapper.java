package com.baraq.ecomm.order.mapper;

import com.baraq.ecomm.order.enums.PaymentMode;
import com.baraq.ecomm.order.persistence.model.Order;
import com.baraq.ecomm.shared.dto.OrderDTO;
import com.baraq.ecomm.shared.dto.ProductDTO;
import com.baraq.ecomm.shared.dto.UserDTO;

public class OrderMapper {

    public static OrderDTO convertToDto(Order order) {
        return  OrderDTO.builder()
                .orderStatus(order.getOrderStatus().name())
                .orderId(order.getOrderId())
                .amount(order.getAmount())
                .quantity(order.getQuantity())
                .payAmount(!PaymentMode.CASH.equals(order.getPaymentMode()))
                .paymentMode(order.getPaymentMode().name())
                .build();
    }
}
