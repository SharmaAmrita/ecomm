package com.baraq.ecomm.order.apis;

import com.baraq.ecomm.order.dto.RequestDTO.PincodeServiceDTO;
import com.baraq.ecomm.order.dto.ResponseDTO.PincodeServiceResponseDTO;
import com.baraq.ecomm.order.service.PincodeAvailabilityService;
import com.baraq.ecomm.product.dto.ResponseDTO.ProductResponseDTO;
import com.baraq.ecomm.utility.GenericResponse;
import com.baraq.ecomm.utility.ResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/availability")
@RequiredArgsConstructor
public class PincodeAvailabilityController {
    private  final PincodeAvailabilityService pincodeAvailabilityService;
    @PostMapping("")
    public ResponseEntity<GenericResponse<PincodeServiceResponseDTO>> createPincodeAvailability(@RequestBody PincodeServiceDTO pincodeServiceDTO) {
        GenericResponse<PincodeServiceResponseDTO> genericResponse = new GenericResponse<PincodeServiceResponseDTO>("successfully added availability",
                ResponseStatus.SUCCESS, pincodeAvailabilityService.createPincodeAvailability(pincodeServiceDTO));
        return new ResponseEntity<>(genericResponse, HttpStatusCode.valueOf(HttpStatus.CREATED.value()));
    }
}
