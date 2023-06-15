package com.baraq.ecomm.order.mapper;

import com.baraq.ecomm.order.dto.ResponseDTO.PincodeServiceResponseDTO;
import com.baraq.ecomm.order.persistence.model.PincodeServiceMapping;

public class PincodeServiceMapper {
    public static PincodeServiceResponseDTO convertToDTO(PincodeServiceMapping pincodeServiceMapping) {
        return PincodeServiceResponseDTO.builder()
                .source(pincodeServiceMapping.getSourcePinCode())
                .destination(pincodeServiceMapping.getDestPinCode())
                .cod(pincodeServiceMapping.getIsCashAvailable())
                .prepaid(pincodeServiceMapping.getIsOnlineAvailable()).build();
    }
}
