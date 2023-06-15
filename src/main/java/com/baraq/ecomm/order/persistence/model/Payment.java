package com.baraq.ecomm.order.persistence.model;

import com.baraq.ecomm.order.enums.PaymentStatus;
import com.baraq.ecomm.shared.persistence.model.AuditEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NonNull;

@Data
@Entity(name = "payments")
@Table(name = "payments")
public class Payment extends AuditEntity {
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id", referencedColumnName = "order_id", nullable = false)
    private Order order;
    @Column(name = "transaction_id")
    private String transactionId;
    @Column(name = "response", columnDefinition = "JSON", nullable = false)
    private String response;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
    @Column(name = "payment_gateway")
    private String paymentGateway;
}
