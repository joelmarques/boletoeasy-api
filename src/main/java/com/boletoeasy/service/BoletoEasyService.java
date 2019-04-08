package com.boletoeasy.service;

import com.boletoeasy.dto.Boleto;
import com.boletoeasy.document.BoletoPaymentDetails;
import reactor.core.publisher.Mono;

import java.util.Collection;

public interface BoletoEasyService {

    Mono<byte[]> create(Boleto boleto);
    Collection<BoletoPaymentDetails> getBoletos(String taxIdentificationNumber);
}
