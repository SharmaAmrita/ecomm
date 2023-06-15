package com.baraq.ecomm.product.mapper;

import com.baraq.ecomm.product.dto.ResponseDTO.ProductResponseDTO;
import com.baraq.ecomm.product.persistence.model.Product;
import com.baraq.ecomm.shared.dto.ProductDTO;

public class ProductMapper {

    public static ProductResponseDTO covertToResponseDTO(Product product) {
        return ProductResponseDTO.builder()
                .productId(product.getId())
                .productName(product.getProductName())
                .inventory(product.getInventory())
                .productCode(product.getProductCode())
                .build();
    }
    public static ProductDTO convertToDto(Product product) {
       return ProductDTO.builder()
                .pincode(product.getPinCode())
                .price(product.getPrice())
                .productName(product.getProductName())
                .productId(product.getId())
                .inventory(product.getInventory()).build();
    }
}
