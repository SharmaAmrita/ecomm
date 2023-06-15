package com.baraq.ecomm.order.dto.RequestDTO;

import lombok.Data;

@Data
public class PincodeServiceDTO {
    private String sourcePincode;
    private String destPincode;
    private Boolean cod;
    private Boolean prepaid;

}
