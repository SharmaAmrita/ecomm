package com.baraq.ecomm.order.persistence.repo;

import com.baraq.ecomm.order.persistence.model.PincodeServiceMapping;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface PincodeAvailabilityRepository extends JpaRepository<PincodeServiceMapping, Long> {
    public Optional<PincodeServiceMapping> findBySourcePinCodeAndDestPinCode(String source, String dest);
}
