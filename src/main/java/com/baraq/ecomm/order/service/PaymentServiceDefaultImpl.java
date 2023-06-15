package com.baraq.ecomm.order.service;

import com.baraq.ecomm.order.dto.RequestDTO.PaymentRequestDTO;
import com.baraq.ecomm.shared.dto.OrderDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceDefaultImpl implements PaymentService {
    @Override
    @Transactional
    public OrderDTO confirmPayment(PaymentRequestDTO paymentRequestDTO) {
        return null;
    }
}
