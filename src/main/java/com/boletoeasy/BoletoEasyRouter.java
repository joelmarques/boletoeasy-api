package com.boletoeasy;

import com.boletoeasy.constants.Constants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class BoletoEasyRouter {

    @Bean
    public RouterFunction<ServerResponse> route(BoletoEasyHandler handler) {
        return RouterFunctions
                .route(POST(Constants.API_BASE_URL).and(accept(MediaType.APPLICATION_JSON)), handler::create);
    }
}
