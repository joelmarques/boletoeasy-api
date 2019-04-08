package com.boletoeasy.generator;

import br.com.caelum.stella.boleto.*;
import br.com.caelum.stella.boleto.bancos.*;
import br.com.caelum.stella.boleto.transformer.GeradorDeBoleto;
import com.boletoeasy.util.BoletoUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DefaultBoletoEasyGenerator implements BoletoEasyGenerator {

    private static final String SEPARATOR = ", ";

    private String join(Object... objects) {
        return StringUtils.joinWith(SEPARATOR, objects);
    }

    @Override
    public byte[] generate(com.boletoeasy.dto.Boleto boletoDTO) {

        String logradouroBeneficiario = this.join(boletoDTO.getPayee().getAddress().getStreet(),
                                                  boletoDTO.getPayee().getAddress().getNumber(),
                                                  boletoDTO.getPayee().getAddress().getComplement());

        Endereco enderecoBeneficiario = Endereco.novoEndereco()
                .comLogradouro(logradouroBeneficiario)
                .comBairro(boletoDTO.getPayee().getAddress().getDistrict())
                .comCep(boletoDTO.getPayee().getAddress().getZipCode())
                .comCidade(boletoDTO.getPayee().getAddress().getCity())
                .comUf(boletoDTO.getPayee().getAddress().getState().name());

        Beneficiario beneficiario = Beneficiario.novoBeneficiario()
                .comNomeBeneficiario(boletoDTO.getPayee().getName())
                .comDocumento(boletoDTO.getPayee().getTaxIdentificationNumber())
                .comAgencia(boletoDTO.getPayeeBankAccount().getAgencyNumber())
                .comDigitoAgencia(boletoDTO.getPayeeBankAccount().getAgencyDV())
                .comCodigoBeneficiario(boletoDTO.getPayeeBankAccount().getAccountNumber())
                .comDigitoCodigoBeneficiario(boletoDTO.getPayeeBankAccount().getAccountDV())
                .comNumeroConvenio(boletoDTO.getPayeeBankAccount().getCovenantNumber())
                .comCarteira(boletoDTO.getPayeeBankAccount().getWalletNumber())
                .comEndereco(enderecoBeneficiario)
                .comNossoNumero("9000206");
                //.comDigitoNossoNumero("5");

        String logradouroPagador = this.join(boletoDTO.getPayer().getAddress().getStreet(),
                                             boletoDTO.getPayer().getAddress().getNumber(),
                                             boletoDTO.getPayer().getAddress().getComplement());

        Endereco enderecoPagador = Endereco.novoEndereco()
                .comLogradouro(logradouroPagador)
                .comBairro(boletoDTO.getPayer().getAddress().getDistrict())
                .comCep(boletoDTO.getPayer().getAddress().getZipCode())
                .comCidade(boletoDTO.getPayer().getAddress().getCity())
                .comUf(boletoDTO.getPayer().getAddress().getState().name());

        Pagador pagador = Pagador.novoPagador()
                .comNome(boletoDTO.getPayer().getName())
                .comDocumento(boletoDTO.getPayer().getTaxIdentificationNumber())
                .comEndereco(enderecoPagador);

        Banco banco = Bancos.getPorNumero(boletoDTO.getPayeeBankAccount().getBankNumber());

        LocalDate vencimento = BoletoUtils.parse(boletoDTO.getDueDate());

        Datas datas = Datas.novasDatas()
                           .comVencimento(vencimento.getDayOfMonth(), vencimento.getMonth().getValue(), vencimento.getYear());

        Boleto boleto = Boleto.novoBoleto()
                .comBanco(banco)
                .comDatas(datas)
                .comBeneficiario(beneficiario)
                .comPagador(pagador)
                .comValorBoleto(boletoDTO.getAmount())
                .comNumeroDoDocumento(boletoDTO.getSequential().toString())
                .comInstrucoes(boletoDTO.getInstructions().stream().limit(5).map(String::new).toArray(String[]::new))
                .comLocaisDePagamento(boletoDTO.getPaymentLocations().stream().limit(2).map(String::new).toArray(String[]::new));

        GeradorDeBoleto gerador = new GeradorDeBoleto(boleto);

        byte[] boletoEmPDF = gerador.geraPDF();

        return boletoEmPDF;
    }
}
