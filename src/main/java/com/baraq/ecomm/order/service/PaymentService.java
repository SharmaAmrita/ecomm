package com.baraq.ecomm.order.service;

import com.baraq.ecomm.order.dto.RequestDTO.PaymentRequestDTO;
import com.baraq.ecomm.shared.dto.OrderDTO;

public interface PaymentService {
    public OrderDTO confirmPayment(PaymentRequestDTO paymentRequestDTO);
}
