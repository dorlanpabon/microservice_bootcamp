package com.pragma.powerup.infrastructure.entrypoints.handler;

import com.pragma.powerup.infrastructure.entrypoints.dto.request.BootcampPageRequestDto;
import com.pragma.powerup.infrastructure.entrypoints.dto.request.BootcampRequestDto;
import com.pragma.powerup.infrastructure.entrypoints.dto.response.BootcampResponseDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IBootcampHandler {

    Mono<Void> saveBootcamp(BootcampRequestDto bootcampRequestDto);

    Flux<BootcampResponseDto> listBootcamps(BootcampPageRequestDto bootcampPageRequestDto);
}