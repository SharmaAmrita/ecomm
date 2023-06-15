package com.baraq.ecomm.user.service;

import com.baraq.ecomm.shared.dto.UserDTO;
import com.baraq.ecomm.user.dto.RequestDTO.CreateUserRequestDto;

public interface UserService {
    public UserDTO createUser(CreateUserRequestDto request);
    public UserDTO findByToken(String token);
}
