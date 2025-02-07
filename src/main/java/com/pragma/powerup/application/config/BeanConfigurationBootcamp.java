package com.pragma.powerup.application.config;

import com.pragma.powerup.domain.api.IBootcampServicePort;
import com.pragma.powerup.domain.spi.IBootcampPersistencePort;
import com.pragma.powerup.domain.spi.ICapacityPersistencePort;
import com.pragma.powerup.domain.usecase.BootcampUseCase;
import com.pragma.powerup.infrastructure.adapters.http.adapter.CapacityPersistenceAdapter;
import com.pragma.powerup.infrastructure.adapters.http.client.CapacityFeignClient;
import com.pragma.powerup.infrastructure.adapters.jpa.adapter.BootcampJpaAdapter;
import com.pragma.powerup.infrastructure.adapters.jpa.mapper.IBootcampEntityMapper;
import com.pragma.powerup.infrastructure.adapters.jpa.repository.IBootcampRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;

@Configuration
@RequiredArgsConstructor
public class BeanConfigurationBootcamp {
    private final IBootcampRepository technologyRepository;
    private final IBootcampEntityMapper technologyEntityMapper;
    private final CapacityFeignClient capacityFeignClient;
    private final HttpServletRequest request;

    @Bean
    public IBootcampPersistencePort technologyPersistencePort() {
        return new BootcampJpaAdapter(technologyRepository, technologyEntityMapper);
    }

    @Bean
    public IBootcampServicePort technologyServicePort() {
        return new BootcampUseCase(technologyPersistencePort(), technologyServiceAdapter());
    }

    @Bean
    public ICapacityPersistencePort technologyServiceAdapter() {
        return new CapacityPersistenceAdapter(capacityFeignClient, request);
    }
}