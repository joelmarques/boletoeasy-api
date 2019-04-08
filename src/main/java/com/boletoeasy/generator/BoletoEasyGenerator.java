package com.boletoeasy.generator;

import com.boletoeasy.dto.Boleto;

public interface BoletoEasyGenerator {

    byte[] generate(Boleto boleto);
}
