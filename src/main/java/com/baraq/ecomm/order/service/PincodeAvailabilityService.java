package com.baraq.ecomm.order.service;

import com.baraq.ecomm.order.dto.RequestDTO.PincodeServiceDTO;
import com.baraq.ecomm.order.dto.ResponseDTO.PincodeServiceResponseDTO;

public interface PincodeAvailabilityService {
    public PincodeServiceResponseDTO createPincodeAvailability(PincodeServiceDTO request);
}
