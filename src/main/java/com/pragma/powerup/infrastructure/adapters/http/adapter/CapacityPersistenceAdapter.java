package com.pragma.powerup.infrastructure.adapters.http.adapter;

import com.pragma.powerup.domain.model.Capacity;
import com.pragma.powerup.domain.spi.ICapacityPersistencePort;
import com.pragma.powerup.infrastructure.adapters.http.client.CapacityFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CapacityPersistenceAdapter implements ICapacityPersistencePort {

    private final CapacityFeignClient capacityFeignClient;
    private final HttpServletRequest request;

    @Override
    public Mono<Boolean> saveCapacitiesBootcamp(Long capacityId, List<Long> technologies) {
        return capacityFeignClient.saveCapacitiesBootcamp(capacityId, technologies)
                .thenReturn(true)
                .onErrorResume(e -> Mono.just(false));
    }

    @Override
    public Flux<Capacity> findCapacitiesByBootcamp(Long id) {
        return capacityFeignClient.findCapacitiesBootcamp(id);
    }

}
