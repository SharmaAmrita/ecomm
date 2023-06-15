package com.baraq.ecomm.product.dto.RequestDTO;

import lombok.Data;

@Data
public class CreateProductRequestDto {
    private String productName;
    private String address;
    private String pincode;
    private String country;
    private String state;
    private Integer quantity;
    private String productCode;
    private Double price;
}
