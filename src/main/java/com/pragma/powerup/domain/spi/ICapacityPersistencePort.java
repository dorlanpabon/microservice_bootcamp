package com.pragma.powerup.domain.spi;

import com.pragma.powerup.domain.model.Capacity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ICapacityPersistencePort {

    Mono<Boolean> saveCapacitiesBootcamp(Long capacityId, List<Long> technologies);

    Flux<Capacity> findCapacitiesByBootcamp(Long id);
}
