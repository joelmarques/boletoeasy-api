package com.boletoeasy.dto;

import lombok.Data;

@Data
public class PayeeBankAccountDTO implements Boleto.PayeeBankAccount {

    private String bankNumber;
    private String agencyNumber;
    private String agencyDV;
    private String accountNumber;
    private String accountDV;
    private String walletNumber;
    private String covenantNumber;
}
