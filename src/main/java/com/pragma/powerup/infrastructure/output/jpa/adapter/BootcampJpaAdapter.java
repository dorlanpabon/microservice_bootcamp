package com.pragma.powerup.infrastructure.output.jpa.adapter;

import com.pragma.powerup.domain.model.Bootcamp;
import com.pragma.powerup.domain.spi.IBootcampPersistencePort;
import com.pragma.powerup.infrastructure.output.jpa.mapper.IBootcampEntityMapper;
import com.pragma.powerup.infrastructure.output.jpa.repository.IBootcampRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class BootcampJpaAdapter implements IBootcampPersistencePort {

    private final IBootcampRepository bootcampRepository;
    private final IBootcampEntityMapper bootcampEntityMapper;

    @Override
    public Mono<Bootcamp> saveBootcamp(Bootcamp bootcamp) {
        return Mono.just(bootcampEntityMapper.toEntity(bootcamp))
                .flatMap(bootcampRepository::save)
                .map(bootcampEntityMapper::toBootcamp);
    }

    @Override
    public Mono<Bootcamp> findBootcampByName(String name) {
        return bootcampRepository.findByName(name)
                .map(bootcampEntityMapper::toBootcamp);
    }

    @Override
    public Flux<Bootcamp> listBootcamps(Integer page, Integer size, String direction, String field) {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.fromString(direction), field);

        return bootcampRepository.findBy(pageable)
                        .map(bootcampEntityMapper::toBootcamp);
    }
}