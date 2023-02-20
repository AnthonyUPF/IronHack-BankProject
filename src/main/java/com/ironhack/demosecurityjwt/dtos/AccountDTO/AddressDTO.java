package com.ironhack.demosecurityjwt.dtos.AccountDTO;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AddressDTO {

    private Integer accountHolderId;
    private String street;
    private String city;
    private String state;

    @Column(length = 5)
    private String zipCode;

    public AddressDTO(Integer accountHolderId, String street, String city, String state, String zip) {
        this.accountHolderId = accountHolderId;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipCode = zip;
    }
}
