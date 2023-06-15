package com.baraq.ecomm.user.service;

import com.baraq.ecomm.shared.dto.UserDTO;
import com.baraq.ecomm.user.dto.RequestDTO.CreateUserRequestDto;
import com.baraq.ecomm.user.mappers.UserMapper;
import com.baraq.ecomm.user.persistence.model.User;
import com.baraq.ecomm.user.persistence.repo.UserRepository;
import com.google.common.base.Preconditions;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceDefaultImpl implements UserService {
    private final UserRepository userRepository;
    @Override
    @Transactional
    public UserDTO createUser(CreateUserRequestDto request) {
        Preconditions.checkArgument(request != null, "Invalid data");
        Preconditions.checkArgument(request.getFirstName() != null, "Please enter valid name");
        Preconditions.checkArgument(request.getApartment() != null, "Please enter valid apartment name");
        Preconditions.checkArgument(request.getCountry() != null, "Please enter valid country");
        Preconditions.checkArgument(request.getPinCode() != null, "Please enter valid pincode");
        Preconditions.checkArgument(request.getState() != null, "Please enter valid state");
        Preconditions.checkArgument(request.getEmail() != null, "Please enter valid email");
        Optional<User> userOptional = userRepository.findByEmail(request.getEmail());
        if(userOptional.isPresent()) {
            return UserMapper.convertToDTO(userOptional.get());
        }
        else {
            User user = new User();
            User createdUser = userRepository.save(user.addUser(request));
            return UserMapper.convertToDTO(createdUser);
        }
    }

    @Override
    public UserDTO findByToken(String token) {
       User user = userRepository.findByUserToken(token)
               .orElseThrow(()->new IllegalArgumentException("Invalid User"));
        return UserMapper.convertToDTO(user);
    }
}
