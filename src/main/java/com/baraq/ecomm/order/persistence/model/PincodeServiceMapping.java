package com.baraq.ecomm.order.persistence.model;

import com.baraq.ecomm.order.dto.RequestDTO.PincodeServiceDTO;
import com.baraq.ecomm.shared.persistence.model.AuditEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity(name = "pincode_service")
@Table(name = "pincode_service")
@Data
public class PincodeServiceMapping extends AuditEntity {
    @Column(name = "source_pincode", nullable = false)
    private String sourcePinCode;
    @Column(name = "destination_pincode", nullable = false)
    private String destPinCode;
    @Column(name = "online_service", nullable = false)
    private Boolean isOnlineAvailable;
    @Column(name = "cod", nullable = false)
    private Boolean isCashAvailable;

    public PincodeServiceMapping addPincodeAvailability(PincodeServiceDTO dto) {
        this.setIsCashAvailable(dto.getCod());
        this.setIsOnlineAvailable(dto.getPrepaid());
        this.setDestPinCode(dto.getDestPincode());
        this.setSourcePinCode(dto.getSourcePincode());
        return this;
    }
}
