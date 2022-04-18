package com.codestates.edastudy.handler;

import com.codestates.edastudy.model.Message;
import java.util.Optional;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class Handler {

    public Mono<ServerResponse> helloMessage(ServerRequest request) {

        Optional<String> query = request.queryParam("name");

        if(query.isPresent()) {
            String name = query.get();
            String hello = "hello" + name;
            Message message = new Message(name, hello);

            return ServerResponse
                    .ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(message);
        }

        return ServerResponse.ok().build();
    }

}
