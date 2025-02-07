package com.pragma.powerup.infrastructure.output.http.adapter;

import com.pragma.powerup.infrastructure.output.http.client.CapacityFeignClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static org.mockito.Mockito.*;

class BootcampPersistenceAdapterTest {

    @Mock
    CapacityFeignClient capacityFeignClient;

    @Mock
    HttpServletRequest request;

    @InjectMocks
    CapacityPersistenceAdapter capacityPersistenceAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveTechnologiesCapacity_Success() {
        when(capacityFeignClient.saveCapacitiesBootcamp(anyLong(), any(List.class)))
                .thenReturn(Mono.just(true));

        Mono<Boolean> result = capacityPersistenceAdapter.saveCapacitiesBootcamp(1L, List.of(1L));

        StepVerifier.create(result)
                .expectNext(true)
                .verifyComplete();

        verify(capacityFeignClient, times(1)).saveCapacitiesBootcamp(1L, List.of(1L));
    }

    @Test
    void testSaveTechnologiesCapacity_ErrorHandling() {
        when(capacityFeignClient.saveCapacitiesBootcamp(anyLong(), any(List.class)))
                .thenReturn(Mono.error(new RuntimeException("Error en Feign")));

        Mono<Boolean> result = capacityPersistenceAdapter.saveCapacitiesBootcamp(1L, List.of(1L));

        StepVerifier.create(result)
                .expectNext(false)
                .verifyComplete();

        verify(capacityFeignClient, times(1)).saveCapacitiesBootcamp(1L, List.of(1L));
    }

    @Test

    void testFindTechnologiesByCapacity() {
        when(capacityFeignClient.findCapacitiesBootcamp(anyLong()))
                .thenReturn(Flux.empty());

        capacityPersistenceAdapter.findCapacitiesByBootcamp(1L);

        verify(capacityFeignClient, times(1)).findCapacitiesBootcamp(1L);
    }
}
