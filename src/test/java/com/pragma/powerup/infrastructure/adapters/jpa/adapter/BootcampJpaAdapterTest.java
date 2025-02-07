package com.pragma.powerup.infrastructure.adapters.jpa.adapter;

import com.pragma.powerup.domain.model.Bootcamp;
import com.pragma.powerup.infrastructure.adapters.jpa.adapter.BootcampJpaAdapter;
import com.pragma.powerup.infrastructure.adapters.jpa.entity.BootcampEntity;
import com.pragma.powerup.infrastructure.adapters.jpa.mapper.IBootcampEntityMapper;
import com.pragma.powerup.infrastructure.adapters.jpa.repository.IBootcampRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.*;

class BootcampJpaAdapterTest {
    @Mock
    IBootcampRepository capacityRepository;
    @Mock
    IBootcampEntityMapper capacityEntityMapper;
    @InjectMocks
    BootcampJpaAdapter capacityJpaAdapter;
    @Mock
    Bootcamp bootcamp;
    @Mock
    BootcampEntity bootcampEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        bootcamp = new Bootcamp();
        bootcamp.setId(1L);
        bootcamp.setName("name");
        bootcamp.setDescription("description");

        bootcampEntity = new BootcampEntity();
        bootcampEntity.setId(1L);
        bootcampEntity.setName("name");
        bootcampEntity.setDescription("description");
    }

    @Test
    void testsaveBootcamp() {
        when(capacityEntityMapper.toEntity(any(Bootcamp.class))).thenReturn(bootcampEntity);
        when(capacityRepository.save(any(BootcampEntity.class))).thenReturn(Mono.just(bootcampEntity));
        when(capacityEntityMapper.toBootcamp(any(BootcampEntity.class))).thenReturn(bootcamp);

        Mono<Bootcamp> result = capacityJpaAdapter.saveBootcamp(bootcamp);

        StepVerifier.create(result)
                .expectNext(bootcamp)
                .verifyComplete();

        verify(capacityEntityMapper, times(1)).toEntity(bootcamp);
        verify(capacityRepository, times(1)).save(bootcampEntity);
    }

    @Test
    void testfindBootcampByName() {
        when(capacityRepository.findByName(anyString())).thenReturn(Mono.empty());

        Mono<Bootcamp> result = capacityJpaAdapter.findBootcampByName("name");

        StepVerifier.create(result)
                .verifyComplete();

        verify(capacityRepository, times(1)).findByName("name");
    }

    @Test
    void testlistBootcamps() {
        when(capacityRepository.findBy(any())).thenReturn(Flux.just(bootcampEntity));
        when(capacityEntityMapper.toBootcamp(any(BootcampEntity.class))).thenReturn(bootcamp);

        Flux<Bootcamp> result = capacityJpaAdapter.listBootcamps(1, 10, "ASC", "name");

        StepVerifier.create(result)
                .expectNext(bootcamp)
                .verifyComplete();

        verify(capacityRepository, times(1)).findBy(any());
    }
}