package com.baraq.ecomm.product.service;

import com.baraq.ecomm.product.dto.RequestDTO.CreateProductRequestDto;
import com.baraq.ecomm.product.dto.ResponseDTO.ProductResponseDTO;
import com.baraq.ecomm.product.mapper.ProductMapper;
import com.baraq.ecomm.product.persistence.model.Product;
import com.baraq.ecomm.product.persistence.repo.ProductRepository;
import com.baraq.ecomm.shared.dto.ProductDTO;
import com.google.common.base.Preconditions;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.locks.ReentrantLock;

@Service
@RequiredArgsConstructor
public class ProductServiceDefaultImpl implements ProductService {

    private final ProductRepository productRepository;
    @Override
    @Transactional
    public ProductResponseDTO createProduct(CreateProductRequestDto request) {
        Preconditions.checkArgument(request != null, "Invalid data");
        Preconditions.checkArgument(StringUtils.isNotEmpty(request.getProductName()), "Please enter product name.");
        Preconditions.checkArgument(request.getAddress() != null, "Please enter valid address");
        Preconditions.checkArgument(request.getPincode() != null, "Please enter valid pincode");
        Preconditions.checkArgument(request.getQuantity() != null && request.getQuantity() >= 1,
                "Quantity cannot be null or less than 1");
        Preconditions.checkArgument(StringUtils.isNotEmpty(request.getProductCode()), "Product code cannot be empty");
        Optional<Product> productOptional = productRepository.findByProductCodeAndPinCode(request.getProductCode(), request.getPincode());
        if(productOptional.isPresent()) {
            productOptional.get().setInventory(productOptional.get().getInventory() + request.getQuantity());
           return ProductMapper.covertToResponseDTO(productRepository.save(productOptional.get()));
        }
        Product product = new Product();
        try {
            return ProductMapper.covertToResponseDTO(productRepository
                    .save(product
                            .addProduct(request)));
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ProductDTO getProductByProductId(Long id) {
       Product product = productRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Invalid product Id"));
       return ProductMapper.convertToDto(product);
    }

    @Override
    public Boolean checkProductAvailabiltiy(Long productId, Integer qty) {
        ReentrantLock lock = new ReentrantLock();
        try {
            lock.lock();
            Product product = productRepository.findById(productId).orElseThrow(()->new IllegalArgumentException("Invalid product id"));
            if(product.getInventory() < qty)
                return false;
            product.setInventory(product.getInventory()-qty);
            productRepository.save(product);
            return true;
        }
        finally {
            lock.unlock();
        }
    }
}
