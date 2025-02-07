package com.pragma.powerup.infrastructure.adapters.http.client;

import com.pragma.powerup.domain.model.Capacity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;


@Component
@ReactiveFeignClient(name = "technology-service", url = "http://localhost:8082")
public interface CapacityFeignClient {

    @PostMapping("bootcamp-capacity/{capacityId}/save")
    Mono<Void> saveCapacitiesBootcamp(@PathVariable Long capacityId, @RequestBody List<Long> technologies);

    @GetMapping("capacity/{id}")
    Flux<Capacity> findCapacitiesBootcamp(@PathVariable Long id);
}
