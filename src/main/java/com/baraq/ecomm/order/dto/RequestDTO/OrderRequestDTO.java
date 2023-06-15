package com.baraq.ecomm.order.dto.RequestDTO;

import com.baraq.ecomm.shared.dto.UserDTO;
import com.baraq.ecomm.user.dto.UserAddressDTO;
import lombok.Data;

import java.util.List;

@Data
public class OrderRequestDTO {
    private UserDTO userDTO;
    private ProductRequest product;
    private String paymentMode;
    private UserAddressDTO destinationAddress;

    @Data
    public class ProductRequest {
        private Long productId;
        private Integer qty;
        private Double amount;
    }
}
