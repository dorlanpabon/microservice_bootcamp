package com.pragma.powerup.domain.api;

import com.pragma.powerup.domain.model.Bootcamp;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IBootcampServicePort {

    Mono<Void> saveBootcamp(Bootcamp bootcamp);

    Flux<Bootcamp> listBootcamps(Integer page, Integer size, String direction, String field);
}