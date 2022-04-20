package com.codestates.edastudy.webClient;

import java.util.Map;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class JobWebClient {

    private final WebClient client;

    public JobWebClient(WebClient.Builder builder) {
        this.client = builder.baseUrl("http://localhost:8081").build();
    }

    public Mono<Map> getJobInfo(String userName) {

        return this.client.get()
                .uri(uriBuilder
                        -> uriBuilder.path("/job")
                        .queryParam("name", userName)
                        .build()
                )
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Map.class);
    }


}
