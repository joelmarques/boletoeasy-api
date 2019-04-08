package com.boletoeasy.document;

public interface BoletoPaymentDetails {

    String getId();
    String getAmount();
    String getPayerName();
    String getPayerTaxIdentificationNumber();
    String getPayeeName();
    String getPayeeTaxIdentificationNumber();
    String getDueDate();
    String getCreatedDate();
    String getPaymentDate();
    PaymentStatus getPaymentStatus();

    enum PaymentStatus {
        PAID,
        UNPAID,
        WAITING;
    }
}
