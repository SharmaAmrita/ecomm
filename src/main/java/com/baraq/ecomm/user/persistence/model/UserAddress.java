package com.baraq.ecomm.user.persistence.model;

import com.baraq.ecomm.shared.persistence.model.AuditEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Data
@Entity(name = "user_address")
@Table(name = "user_address")
public class UserAddress extends AuditEntity {
    @Column(name = "apartment_name", nullable = false)
    private String apartment;
    @Column(name = "street", nullable = false)
    private String street;
    @Column(name = "pincode", nullable = false)
    private String pinCode;
    @Column(name = "country", nullable = false)
    private String country;
    @Column(name = "state", nullable = false)
    private String state;
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;
}
