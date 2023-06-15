package com.baraq.ecomm.user.dto.RequestDTO;

import lombok.Data;

@Data
public class CreateUserRequestDto {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String apartment;
    private String street;
    private String country;
    private String pinCode;
    private String state;

}
