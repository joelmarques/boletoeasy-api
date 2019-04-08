package com.boletoeasy.service;

import com.boletoeasy.generator.BoletoEasyGenerator;
import com.boletoeasy.dto.Boleto;
import com.boletoeasy.document.BoletoPaymentDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Collection;

@Service("boletoEasyService")
public class DefaultBoletoEasyService implements BoletoEasyService {

    @Autowired
    private BoletoEasyGenerator boletoEasyGenerator;

    @Override
    public Mono<byte[]> create(Boleto boleto) {

        return Mono.just(this.boletoEasyGenerator.generate(boleto));
    }

    //TODO
    @Override
    public Collection<BoletoPaymentDetails> getBoletos(String taxIdentificationNumber) {
        return null;
    }
}
