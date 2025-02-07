package com.pragma.powerup.domain.spi;

import com.pragma.powerup.domain.model.Bootcamp;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IBootcampPersistencePort {

    Mono<Bootcamp> saveBootcamp(Bootcamp bootcamp);

    Mono<Bootcamp> findBootcampByName(String name);

    Flux<Bootcamp> listBootcamps(Integer page, Integer size, String direction, String field);
}