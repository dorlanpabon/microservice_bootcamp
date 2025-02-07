package com.pragma.powerup.infrastructure.entrypoints;

import com.pragma.powerup.infrastructure.entrypoints.dto.request.BootcampPageRequestDto;
import com.pragma.powerup.infrastructure.entrypoints.dto.request.BootcampRequestDto;
import com.pragma.powerup.infrastructure.entrypoints.dto.response.BootcampResponseDto;
import com.pragma.powerup.infrastructure.entrypoints.handler.IBootcampHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class BootcampRestControllerTest {
    @Mock
    IBootcampHandler capacityHandler;

    @InjectMocks
    BootcampRestController bootcampRestController;

    @Mock
    BootcampRequestDto bootcampRequestDto;

    @Mock
    BootcampPageRequestDto bootcampPageRequestDto;

    @Mock
    BootcampResponseDto bootcampResponseDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        bootcampRequestDto = new BootcampRequestDto();
        bootcampRequestDto.setName("Todo");
        bootcampRequestDto.setDescription("Todo");

        bootcampPageRequestDto = new BootcampPageRequestDto();
        bootcampPageRequestDto.setPage(1);
        bootcampPageRequestDto.setSize(10);
        bootcampPageRequestDto.setDirection("ASC");

        bootcampResponseDto = new BootcampResponseDto();
        bootcampResponseDto.setId(1L);
        bootcampResponseDto.setName("Todo");
        bootcampResponseDto.setDescription("Todo");
    }

    @Test
    void testsaveCapacity() {
        when(capacityHandler.saveBootcamp(any(BootcampRequestDto.class))).thenReturn(Mono.empty());

        Mono<Void> result = bootcampRestController.saveCapacity(bootcampRequestDto);

        assertNotNull(result);

        StepVerifier.create(result)
                .verifyComplete();

        verify(capacityHandler, times(1)).saveBootcamp(any(BootcampRequestDto.class));
    }

    @Test
    void testlistCapacities() {
        when(capacityHandler.listBootcamps(any())).thenReturn(Flux.just(bootcampResponseDto));

        Flux<BootcampResponseDto>  result = bootcampRestController.listCapacities(bootcampPageRequestDto);

        assertNotNull(result);

        result.subscribe(response -> assertEquals("Todo", response.getName()));

        verify(capacityHandler, times(1)).listBootcamps(any());
    }
}
