package com.baraq.ecomm.shared.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDTO {
    private Long productId;
    private String productName;
    private String pincode;
    private Integer inventory;
    private Double price;
}
