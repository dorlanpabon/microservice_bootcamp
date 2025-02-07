package com.pragma.powerup.infrastructure.entrypoints.handler;

import com.pragma.powerup.infrastructure.entrypoints.dto.request.BootcampPageRequestDto;
import com.pragma.powerup.infrastructure.entrypoints.dto.request.BootcampRequestDto;
import com.pragma.powerup.infrastructure.entrypoints.dto.response.BootcampResponseDto;
import com.pragma.powerup.infrastructure.entrypoints.handler.impl.BootcampHandler;
import com.pragma.powerup.infrastructure.entrypoints.mapper.IBootcampRequestMapper;
import com.pragma.powerup.infrastructure.entrypoints.mapper.IBootcampResponseMapper;
import com.pragma.powerup.domain.api.IBootcampServicePort;
import com.pragma.powerup.domain.model.Bootcamp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.*;

class BootcampHandlerTest {
    @Mock
    IBootcampServicePort capacityServicePort;
    @Mock
    IBootcampRequestMapper capacityRequestMapper;
    @Mock
    IBootcampResponseMapper capacityResponseMapper;
    @InjectMocks
    BootcampHandler capacityHandler;
    @Mock
    BootcampRequestDto bootcampRequestDto;
    @Mock
    BootcampResponseDto bootcampResponseDto;
    @Mock
    BootcampPageRequestDto bootcampPageRequestDto;
    @Mock
    Bootcamp bootcamp;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        bootcampRequestDto = new BootcampRequestDto();
        bootcampRequestDto.setName("name");
        bootcampRequestDto.setDescription("description");

        bootcamp = new Bootcamp();
        bootcamp.setId(1L);
        bootcamp.setName("name");
        bootcamp.setDescription("description");

        bootcampResponseDto = new BootcampResponseDto();
        bootcampResponseDto.setId(1L);
        bootcampResponseDto.setName("name");
        bootcampResponseDto.setDescription("description");

        bootcampPageRequestDto = new BootcampPageRequestDto();
        bootcampPageRequestDto.setPage(1);
        bootcampPageRequestDto.setSize(10);
        bootcampPageRequestDto.setDirection("ASC");
        bootcampPageRequestDto.setField("name");
    }

    @Test
    void testsaveBootcamp() {
        when(capacityRequestMapper.toBootcamp(bootcampRequestDto)).thenReturn(bootcamp);
        when(capacityServicePort.saveBootcamp(any())).thenReturn(Mono.empty());

        Mono<Void> result = capacityHandler.saveBootcamp(bootcampRequestDto);

        StepVerifier.create(result)
                .verifyComplete();

        verify(capacityRequestMapper, times(1)).toBootcamp(bootcampRequestDto);
        verify(capacityServicePort, times(1)).saveBootcamp(any());
    }

    @Test
    void testlistBootcamps() {
        when(capacityServicePort.listBootcamps(1, 10, "ASC", "name")).thenReturn(Flux.just(bootcamp));
        when(capacityResponseMapper.toBootcampResponseDto(bootcamp)).thenReturn(bootcampResponseDto);

        Flux<BootcampResponseDto> result = capacityHandler.listBootcamps(bootcampPageRequestDto);

        StepVerifier.create(result)
                .expectNext(bootcampResponseDto)
                .verifyComplete();

        verify(capacityServicePort, times(1)).listBootcamps(1, 10, "ASC", "name");
        verify(capacityResponseMapper, times(1)).toBootcampResponseDto(bootcamp);
    }
}