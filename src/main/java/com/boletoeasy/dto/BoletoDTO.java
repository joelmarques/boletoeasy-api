package com.boletoeasy.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Collection;

@Data
public class BoletoDTO implements Boleto {

    private Long sequential;
    private BigDecimal amount;
    private String dueDate;

    @JsonDeserialize(as = PersonDTO.class)
    private Person payer;

    @JsonDeserialize(as = PersonDTO.class)
    private Person payee;

    @JsonDeserialize(as = PayeeBankAccountDTO.class)
    private PayeeBankAccount payeeBankAccount;

    private Collection<String> instructions;

    private Collection<String> paymentLocations;
}
