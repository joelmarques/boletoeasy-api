package com.boletoeasy.dto;

import lombok.Data;

@Data
public class AddressDTO implements Boleto.Person.Address {

    private String zipCode;
    private String street;
    private String number;
    private String district;
    private String complement;
    private String city;
    private State state;
}
