package com.baraq.ecomm.product.service;

import com.baraq.ecomm.product.dto.RequestDTO.CreateProductRequestDto;
import com.baraq.ecomm.product.dto.ResponseDTO.ProductResponseDTO;
import com.baraq.ecomm.shared.dto.ProductDTO;

public interface ProductService {

    public ProductResponseDTO createProduct(CreateProductRequestDto request);
    public ProductDTO getProductByProductId(Long id);
    public Boolean checkProductAvailabiltiy(Long productId, Integer qty);
}
