package com.baraq.ecomm.user.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserAddressDTO {
    private String apartment;
    private String street;
    private String state;
    private String country;
    private String pincode;
    private Long id;
}
