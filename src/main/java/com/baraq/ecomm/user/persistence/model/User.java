package com.baraq.ecomm.user.persistence.model;

import com.baraq.ecomm.shared.persistence.model.AuditEntity;
import com.baraq.ecomm.user.dto.RequestDTO.CreateUserRequestDto;
import com.baraq.ecomm.user.dto.UserAddressDTO;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "user")
@Data
@Table(name = "user")
public class User extends AuditEntity {
    @Column(name = "firstname", nullable = false)
    private String firstName;
    @Column(name="lastname")
    private String lastName;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "phone")
    private String phone;
    @OneToMany(mappedBy = "user", orphanRemoval = true, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<UserAddress> addresses = new ArrayList<>();
    @Column(name="user_token", nullable = false)
    private String userToken;

    public String generateFullName() {
        if(this.lastName != null)
            return this.firstName+ " "+ this.lastName;
        else
            return this.firstName;
    }

    private void setUserToken() {
        UUID uuid = UUID.randomUUID();
        this.userToken = uuid.toString();
    }

    public User addUser(CreateUserRequestDto createUserRequestDto) {
        this.setFirstName(createUserRequestDto.getFirstName());
        this.setLastName(createUserRequestDto.getLastName());
        this.setEmail(createUserRequestDto.getEmail());
        this.setPhone(createUserRequestDto.getPhone());
        this.addAddress(UserAddressDTO.builder()
                .state(createUserRequestDto.getState())
                .pincode(createUserRequestDto.getPinCode())
                .country(createUserRequestDto.getCountry())
                .street(createUserRequestDto.getStreet())
                .apartment(createUserRequestDto.getApartment())
                .build());
        // this can be authentication mechanism, handled separately. this is only for example
        setUserToken();
        return this;
    }

    public void addAddress(UserAddressDTO userAddressDTO) {
        UserAddress address = new UserAddress();
        address.setUser(this);
        address.setApartment(userAddressDTO.getApartment());
        address.setCountry(userAddressDTO.getCountry());
        address.setStreet(userAddressDTO.getStreet());
        address.setState(userAddressDTO.getState());
        address.setPinCode(userAddressDTO.getPincode());
        this.addresses.add(address);
    }
}
