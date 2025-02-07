package com.pragma.powerup.infrastructure.output.jpa.repository;

import com.pragma.powerup.infrastructure.output.jpa.entity.BootcampEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface IBootcampRepository extends ReactiveCrudRepository<BootcampEntity, Long> {

    Mono<BootcampEntity> findByName(String name);

    Flux<BootcampEntity> findBy(Pageable pageable);

}