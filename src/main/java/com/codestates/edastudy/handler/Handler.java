package com.codestates.edastudy.handler;

import com.codestates.edastudy.model.Message;
import com.codestates.edastudy.webClient.JobWebClient;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class Handler {

    @Autowired
    private JobWebClient jobWebClient;

    public Mono<ServerResponse> helloMessage(ServerRequest request) {

        Optional<String> query = request.queryParam("name");

        if (query.isPresent()) {
            String name = query.get();
            String hello = "hello" + name;

            // WebClient 사용
            Mono<Message> msg = jobWebClient.getJobInfo(name).map(d -> {
                String job = d.get("job").toString();
                return new Message(name, hello, job);
            });

            return ServerResponse
                    .ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(msg, Message.class);
        }

        return ServerResponse.ok().build();
    }

}
