package com.baraq.ecomm.product.persistence.repo;

import com.baraq.ecomm.product.persistence.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    public Optional<Product> findByProductCodeAndPinCode(String productCode, String pinCode);
}
