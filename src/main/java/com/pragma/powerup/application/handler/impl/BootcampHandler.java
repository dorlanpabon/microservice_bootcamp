package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.dto.request.BootcampPageRequestDto;
import com.pragma.powerup.application.dto.request.BootcampRequestDto;
import com.pragma.powerup.application.dto.response.BootcampResponseDto;
import com.pragma.powerup.application.handler.IBootcampHandler;
import com.pragma.powerup.application.mapper.IBootcampRequestMapper;
import com.pragma.powerup.application.mapper.IBootcampResponseMapper;
import com.pragma.powerup.domain.api.IBootcampServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Transactional
public class BootcampHandler implements IBootcampHandler {

    private final IBootcampServicePort capacityServicePort;
    private final IBootcampRequestMapper capacityRequestMapper;
    private final IBootcampResponseMapper capacityResponseMapper;

    @Override
    public Mono<Void> saveBootcamp(BootcampRequestDto bootcampRequestDto) {
        return Mono.just(bootcampRequestDto)
                .map(capacityRequestMapper::toBootcamp)
                .flatMap(capacityServicePort::saveBootcamp)
                .then();
    }

    @Override
    public Flux<BootcampResponseDto> listBootcamps(BootcampPageRequestDto bootcampPageRequestDto) {
        return capacityServicePort.listBootcamps(bootcampPageRequestDto.getPage(), bootcampPageRequestDto.getSize(), bootcampPageRequestDto.getDirection(), bootcampPageRequestDto.getField())
                .map(capacityResponseMapper::toBootcampResponseDto);
    }


}