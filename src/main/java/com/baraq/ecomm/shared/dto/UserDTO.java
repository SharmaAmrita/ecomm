package com.baraq.ecomm.shared.dto;

import com.baraq.ecomm.user.dto.UserAddressDTO;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserDTO {
    private Long userId;
    private String userName;
    private List<UserAddressDTO> userAddressDTOs;
}
