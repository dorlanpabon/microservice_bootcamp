package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.exception.DomainException;
import com.pragma.powerup.domain.model.Bootcamp;
import com.pragma.powerup.domain.spi.IBootcampPersistencePort;
import com.pragma.powerup.domain.spi.ICapacityPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;

import static org.mockito.Mockito.*;

class BootcampUseCaseTest {

    @Mock
    IBootcampPersistencePort capacityPersistencePort;

    @Mock
    ICapacityPersistencePort technologyPersistencePort;

    @InjectMocks
    BootcampUseCase bootcampUseCase;

    private Bootcamp bootcamp;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        bootcamp = new Bootcamp();
        bootcamp.setId(1L);
        bootcamp.setName("name");
        bootcamp.setDescription("description");

        List<Long> technologies = List.of(1L, 2L,3L);

        bootcamp.setCapacities(technologies);
    }

    @Test
    void testsaveBootcamp_Success() {
        when(capacityPersistencePort.findBootcampByName(anyString())).thenReturn(Mono.empty());
        when(capacityPersistencePort.saveBootcamp(any(Bootcamp.class))).thenReturn(Mono.just(bootcamp));
        when(technologyPersistencePort.saveCapacitiesBootcamp(anyLong(), anyList())).thenReturn(Mono.empty());

        Mono<Void> result = bootcampUseCase.saveBootcamp(bootcamp);

        StepVerifier.create(result)
                .verifyComplete();

        verify(capacityPersistencePort, times(1)).findBootcampByName("name");
        verify(capacityPersistencePort, times(1)).saveBootcamp(bootcamp);
    }

    @Test
    void testsaveBootcamp_AlreadyExists() {
        when(capacityPersistencePort.findBootcampByName(any())).thenReturn(Mono.just(bootcamp));
        when(capacityPersistencePort.saveBootcamp(any(Bootcamp.class))).thenReturn(Mono.just(bootcamp));

        Mono<Void> result = bootcampUseCase.saveBootcamp(bootcamp);

        StepVerifier.create(result)
                .expectError(DomainException.class)
                .verify();

        verify(capacityPersistencePort, times(1)).findBootcampByName("name");
    }

    @Test
    void testsaveBootcamp_NameNull() {
        bootcamp.setName(null);

        Mono<Void> result = bootcampUseCase.saveBootcamp(bootcamp);

        StepVerifier.create(result)
                .expectError(DomainException.class)
                .verify();

        verify(capacityPersistencePort, never()).findBootcampByName(anyString());
        verify(capacityPersistencePort, never()).saveBootcamp(any(Bootcamp.class));
    }

    @Test
    void testsaveBootcamp_NameTooLong() {
        bootcamp.setName("a".repeat(51));

        Mono<Void> result = bootcampUseCase.saveBootcamp(bootcamp);

        StepVerifier.create(result)
                .expectError(DomainException.class)
                .verify();

        verify(capacityPersistencePort, never()).findBootcampByName(anyString());
        verify(capacityPersistencePort, never()).saveBootcamp(any(Bootcamp.class));
    }

    @Test
    void testsaveBootcamp_DescriptionNull() {
        bootcamp.setDescription(null);

        Mono<Void> result = bootcampUseCase.saveBootcamp(bootcamp);

        StepVerifier.create(result)
                .expectError(DomainException.class)
                .verify();

        verify(capacityPersistencePort, never()).findBootcampByName(anyString());
        verify(capacityPersistencePort, never()).saveBootcamp(any(Bootcamp.class));
    }

    @Test
    void testsaveBootcamp_DescriptionTooLong() {
        bootcamp.setDescription("a".repeat(91));

        Mono<Void> result = bootcampUseCase.saveBootcamp(bootcamp);

        StepVerifier.create(result)
                .expectError(DomainException.class)
                .verify();

        verify(capacityPersistencePort, never()).findBootcampByName(anyString());
        verify(capacityPersistencePort, never()).saveBootcamp(any(Bootcamp.class));
    }

    @Test
    void testsaveBootcamp_TechnologiesNull() {
        bootcamp.setTechnologies(null);

        Mono<Void> result = bootcampUseCase.saveBootcamp(bootcamp);

        StepVerifier.create(result)
                .expectError(DomainException.class)
                .verify();

        verify(capacityPersistencePort, never()).findBootcampByName(anyString());
        verify(capacityPersistencePort, never()).saveBootcamp(any(Bootcamp.class));
    }

    @Test
    void testsaveBootcamp_TechnologiesEmpty() {
        bootcamp.setCapacities(List.of());

        Mono<Void> result = bootcampUseCase.saveBootcamp(bootcamp);

        StepVerifier.create(result)
                .expectError(DomainException.class)
                .verify();

        verify(capacityPersistencePort, never()).findBootcampByName(anyString());
        verify(capacityPersistencePort, never()).saveBootcamp(any(Bootcamp.class));
    }

    @Test
    void testsaveBootcamp_TechnologiesContainsZero() {
        bootcamp.setCapacities(List.of(1L, 0L));

        Mono<Void> result = bootcampUseCase.saveBootcamp(bootcamp);

        StepVerifier.create(result)
                .expectError(DomainException.class)
                .verify();

        verify(capacityPersistencePort, never()).findBootcampByName(anyString());
        verify(capacityPersistencePort, never()).saveBootcamp(any(Bootcamp.class));
    }

    @Test
    void testlistBootcamps() {
        when(capacityPersistencePort.listBootcamps(anyInt(), anyInt(), anyString(), anyString())).thenReturn(Flux.just(bootcamp));
        when(technologyPersistencePort.findCapacitiesByBootcamp(anyLong())).thenReturn(Flux.just());

        Flux<Bootcamp> result = bootcampUseCase.listBootcamps(1, 10, "ASC", "name");

        StepVerifier.create(result)
                .expectNext(bootcamp)
                .verifyComplete();

        verify(capacityPersistencePort, times(1)).listBootcamps(1, 10, "ASC", "name");
    }
}
