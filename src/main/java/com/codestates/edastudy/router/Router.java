package com.codestates.edastudy.router;

import com.codestates.edastudy.handler.Handler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
@EnableWebFlux
public class Router {

    @Bean
    public RouterFunction<ServerResponse> helloRouter(Handler handler) {
        return RouterFunctions.route()
                .GET("/hello", handler::helloMessage)
                .build();
    }
}
