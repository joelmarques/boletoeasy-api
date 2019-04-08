package com.boletoeasy.dto;

import java.math.BigDecimal;
import java.util.Collection;

public interface Boleto {

    static final String DATE_PATTERN = "dd/MM/yyyy";

    Long getSequential();
    BigDecimal getAmount();
    String getDueDate();
    Person getPayer();
    Person getPayee();
    PayeeBankAccount getPayeeBankAccount();
    Collection<String> getInstructions();
    Collection<String> getPaymentLocations();

    interface Person {

        String getName();
        String getTaxIdentificationNumber();
        Address getAddress();

        interface Address {

            String getZipCode();
            String getStreet();
            String getNumber();
            String getDistrict();
            String getComplement();
            String getCity();
            State getState();

            enum State {
                AC,AL,AM,AP,BA,CE,DF,ES,GO,MA,MG,MS,MT,PA,PB,PE,PI,PR,RJ,RN,RO,RR,RS,SC,SE,SP,TO;
            }
        }
    }

    interface PayeeBankAccount {

        String getBankNumber();
        String getAgencyNumber();
        String getAgencyDV();
        String getAccountNumber();
        String getAccountDV();
        String getWalletNumber();
        String getCovenantNumber();
    }
}
