package com.boletoeasy.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

@Data
public class PersonDTO implements Boleto.Person {

    private String name;
    private String taxIdentificationNumber;

    @JsonDeserialize(as = AddressDTO.class)
    private Address address;
}
