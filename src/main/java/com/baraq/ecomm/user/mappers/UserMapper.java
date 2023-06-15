package com.baraq.ecomm.user.mappers;

import com.baraq.ecomm.shared.dto.UserDTO;
import com.baraq.ecomm.user.dto.UserAddressDTO;
import com.baraq.ecomm.user.persistence.model.User;
import com.baraq.ecomm.user.persistence.model.UserAddress;

import java.util.stream.Collectors;

public class UserMapper {

    public static UserDTO convertToDTO(User user) {
        return UserDTO.builder()
                .userId(user.getId())
                .userName(user.generateFullName())
                .userAddressDTOs(user.getAddresses()
                        .stream()
                        .map(UserMapper::convertToUserAddressDTO)
                        .collect(Collectors.toList())).build();
    }

    public static UserAddressDTO convertToUserAddressDTO(UserAddress userAddress) {
       return UserAddressDTO.builder()
                .apartment(userAddress.getApartment())
                .state(userAddress.getState())
                .id(userAddress.getId())
                .country(userAddress.getCountry())
                .pincode(userAddress.getPinCode())
                .street(userAddress.getStreet()).build();
    }
}
