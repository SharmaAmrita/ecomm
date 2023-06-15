package com.baraq.ecomm.order.apis;

import com.baraq.ecomm.order.dto.RequestDTO.OrderRequestDTO;
import com.baraq.ecomm.order.service.OrderService;
import com.baraq.ecomm.shared.dto.OrderDTO;
import com.baraq.ecomm.shared.dto.UserDTO;
import com.baraq.ecomm.shared.exception.UnauthorizedException;
import com.baraq.ecomm.user.service.UserService;
import com.baraq.ecomm.utility.GenericResponse;
import com.baraq.ecomm.utility.ResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import javax.naming.AuthenticationException;

@RestController
@RequestMapping("/v1/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;
    @PostMapping("")
    public ResponseEntity<GenericResponse<OrderDTO>> createOrder(@RequestBody OrderRequestDTO orderRequestDTO,
                                                                 @RequestHeader("x-user-id") String userToken) {
        try {
           UserDTO userDTO = userService.findByToken(userToken);
           orderRequestDTO.setUserDTO(userDTO);
        }
        catch (Exception e) {
            throw new UnauthorizedException("Invalid user");
        }
        GenericResponse<OrderDTO> genericResponse = new GenericResponse<OrderDTO>("successfully created order",
                ResponseStatus.SUCCESS, orderService.createOrder(orderRequestDTO));
        return new ResponseEntity<>(genericResponse, HttpStatusCode.valueOf(HttpStatus.CREATED.value()));
    }
}
