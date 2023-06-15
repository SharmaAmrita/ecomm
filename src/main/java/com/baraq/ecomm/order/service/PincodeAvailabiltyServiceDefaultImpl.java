package com.baraq.ecomm.order.service;

import com.baraq.ecomm.order.dto.RequestDTO.PincodeServiceDTO;
import com.baraq.ecomm.order.dto.ResponseDTO.PincodeServiceResponseDTO;
import com.baraq.ecomm.order.mapper.PincodeServiceMapper;
import com.baraq.ecomm.order.persistence.model.PincodeServiceMapping;
import com.baraq.ecomm.order.persistence.repo.PincodeAvailabilityRepository;
import com.google.common.base.Preconditions;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PincodeAvailabiltyServiceDefaultImpl implements PincodeAvailabilityService {
    private final PincodeAvailabilityRepository pincodeAvailabilityRepository;

    @Override
    @Transactional
    public PincodeServiceResponseDTO createPincodeAvailability(PincodeServiceDTO request) {
        Preconditions.checkArgument(request != null, "Invalid Request");
        Preconditions.checkArgument(StringUtils.isNotEmpty(request.getDestPincode()), "Enter valid destination pincode");
        Preconditions.checkArgument(StringUtils.isNotEmpty(request.getSourcePincode()), "Enter valid source pincode");
        Preconditions.checkArgument(request.getCod() != null, "cod cannot be null");
        Preconditions.checkArgument(request.getPrepaid() != null, "prepaid cannot be null");
        Optional<PincodeServiceMapping> pincodeServiceMappingOptional = pincodeAvailabilityRepository
                .findBySourcePinCodeAndDestPinCode(request.getSourcePincode(), request.getDestPincode());
        if(pincodeServiceMappingOptional.isPresent()) {
            pincodeServiceMappingOptional.get().setIsOnlineAvailable(request.getPrepaid());
            pincodeServiceMappingOptional.get().setIsCashAvailable(request.getCod());
            return PincodeServiceMapper.convertToDTO(pincodeAvailabilityRepository.save(pincodeServiceMappingOptional.get()));
        }
        PincodeServiceMapping pincodeServiceMapping = new PincodeServiceMapping();
        try {
            return PincodeServiceMapper.convertToDTO(pincodeAvailabilityRepository
                    .save(pincodeServiceMapping
                            .addPincodeAvailability(request)));
        }
        catch (Exception e) {
            throw  new RuntimeException(e);
        }
    }
}
