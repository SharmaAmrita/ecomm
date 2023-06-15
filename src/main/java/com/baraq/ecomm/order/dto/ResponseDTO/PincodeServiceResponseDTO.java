package com.baraq.ecomm.order.dto.ResponseDTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PincodeServiceResponseDTO {
    private String source;
    private String destination;
    private Boolean cod;
    private Boolean prepaid;
}
