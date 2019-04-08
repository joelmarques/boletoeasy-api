package com.boletoeasy;

import com.boletoeasy.dto.Boleto;
import com.boletoeasy.dto.BoletoDTO;
import com.boletoeasy.service.BoletoEasyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class BoletoEasyHandler {

    @Autowired
    private BoletoEasyService boletoEasyService;

    public Mono<ServerResponse> create(ServerRequest request) {

        final Mono<Boleto> boleto = request.bodyToMono(BoletoDTO.class);

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .body(BodyInserters.fromPublisher(boleto.flatMap(boletoEasyService::create), byte[].class));
    }
 }
