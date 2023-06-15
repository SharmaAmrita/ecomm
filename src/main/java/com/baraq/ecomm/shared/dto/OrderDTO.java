package com.baraq.ecomm.shared.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderDTO {
    private String orderId;
    private String orderStatus;
    private Integer quantity;
    private String paymentMode;
    private Boolean payAmount;
    private Double amount;
}
