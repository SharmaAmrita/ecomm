package com.baraq.ecomm.product.dto.ResponseDTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductResponseDTO {
    private Long productId;
    private String productName;
    private Integer inventory;
    private String productCode;
    private Double price;
}
