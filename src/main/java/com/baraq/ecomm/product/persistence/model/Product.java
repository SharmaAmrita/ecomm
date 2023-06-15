package com.baraq.ecomm.product.persistence.model;

import com.baraq.ecomm.product.dto.RequestDTO.CreateProductRequestDto;
import com.baraq.ecomm.shared.persistence.model.AuditEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity (name = "product")
@Table(name = "product")
public class Product extends AuditEntity {
    @Column(name = "product_name", nullable = false)
    private String productName;
    @Column(name = "inventory", nullable = false)
    private Integer inventory;
    @Column(name = "address")
    private String address;
    @Column(name = "state", nullable = false)
    private String state;
    @Column(name = "country")
    private String country;
    @Column(name = "pincode", nullable = false)
    private String pinCode;
    @Column(name = "product_code", nullable = false)
    private String productCode;
    @Column(name = "price", nullable = false)
    private Double price;

    public Product addProduct(CreateProductRequestDto dto) {
      this.setProductName(dto.getProductName());
      this.setCountry(dto.getCountry());
      this.setAddress(dto.getAddress());
      this.setInventory(dto.getQuantity());
      this.setPinCode(dto.getPincode());
      this.setState(dto.getState());
      this.setProductCode(dto.getProductCode());
      this.setPrice(dto.getPrice());
      return this;
    }
}
