package com.baraq.ecomm.order.persistence.model;

import com.baraq.ecomm.order.dto.RequestDTO.OrderRequestDTO;
import com.baraq.ecomm.order.enums.OrderStatus;
import com.baraq.ecomm.order.enums.PaymentMode;
import com.baraq.ecomm.shared.persistence.model.AuditEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity(name = "orders")
@Table(name = "orders")
@Data
public class Order extends AuditEntity {
    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "user_id", nullable = false)
    private Long userId;
    @Column(name = "order_id", nullable = false)
    private String orderId;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "order_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Column(name = "payment_mode", nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentMode paymentMode;
    @Column(name = "amount", nullable = false)
    private Double amount;
    @Column(name = "is_paid", nullable = false)
    private Boolean isPaid;


    public void setOrderId() {
        UUID uuid = UUID.randomUUID();
        this.orderId = uuid.toString();
    }

    public Order addOrder(OrderRequestDTO requestDTO) {
        this.setOrderId();
        this.setOrderStatus(PaymentMode.CASH.equals(PaymentMode.getPaymentMode(requestDTO.getPaymentMode()))?
                OrderStatus.PLACED: OrderStatus.PAYMENT_PENDING);
        this.setQuantity(requestDTO.getProduct().getQty());
        this.setAmount(requestDTO.getProduct().getAmount());
        this.setPaymentMode(PaymentMode.getPaymentMode(requestDTO.getPaymentMode()));
        this.setUserId(requestDTO.getUserDTO().getUserId());
        this.setProductId(requestDTO.getProduct().getProductId());
        this.setIsPaid(false);
        return this;
    }
}
