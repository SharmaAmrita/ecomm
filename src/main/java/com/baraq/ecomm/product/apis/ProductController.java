package com.baraq.ecomm.product.apis;

import com.baraq.ecomm.product.dto.RequestDTO.CreateProductRequestDto;
import com.baraq.ecomm.product.dto.ResponseDTO.ProductResponseDTO;
import com.baraq.ecomm.product.service.ProductService;
import com.baraq.ecomm.utility.GenericResponse;
import com.baraq.ecomm.utility.ResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    @PostMapping("")
    public ResponseEntity<GenericResponse<ProductResponseDTO>> createProduct(@RequestBody CreateProductRequestDto requestDto) {
        GenericResponse<ProductResponseDTO> genericResponse = new GenericResponse<ProductResponseDTO>("successfully created product",
               ResponseStatus.SUCCESS, productService.createProduct(requestDto));
        return new ResponseEntity<>(genericResponse, HttpStatusCode.valueOf(HttpStatus.CREATED.value()));
    }
}
