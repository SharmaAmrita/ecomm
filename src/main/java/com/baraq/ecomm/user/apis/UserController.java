package com.baraq.ecomm.user.apis;

import com.baraq.ecomm.shared.dto.UserDTO;
import com.baraq.ecomm.user.dto.RequestDTO.CreateUserRequestDto;
import com.baraq.ecomm.user.service.UserService;
import com.baraq.ecomm.utility.GenericResponse;
import com.baraq.ecomm.utility.ResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @PostMapping("")
    public ResponseEntity<GenericResponse<UserDTO>> createUser(@RequestBody CreateUserRequestDto request) {
        GenericResponse<UserDTO> genericResponse = new GenericResponse<UserDTO>("Successfully created user.",
                ResponseStatus.SUCCESS, userService.createUser(request));
        return new ResponseEntity<>(genericResponse, HttpStatusCode.valueOf(HttpStatus.OK.value()));
    }
}
