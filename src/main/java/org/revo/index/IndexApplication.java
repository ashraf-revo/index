package org.revo.index;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;
import static reactor.core.publisher.Flux.fromIterable;

@SpringBootApplication
@EnableDiscoveryClient
public class IndexApplication {

    public static void main(String[] args) {
        SpringApplication.run(IndexApplication.class, args);
    }


    @Bean
    public RouterFunction<ServerResponse> function(DiscoveryClient discoveryClient) {
        return route(GET("/"), serverRequest -> ok().body(fromIterable(discoveryClient.getServices()), String.class));
    }
}

