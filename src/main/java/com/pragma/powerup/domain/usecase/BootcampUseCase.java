package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.IBootcampServicePort;
import com.pragma.powerup.domain.constants.DomainConstants;
import com.pragma.powerup.domain.exception.DomainException;
import com.pragma.powerup.domain.model.Bootcamp;
import com.pragma.powerup.domain.model.PaginationParams;
import com.pragma.powerup.domain.spi.IBootcampPersistencePort;
import com.pragma.powerup.domain.spi.ICapacityPersistencePort;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class BootcampUseCase implements IBootcampServicePort {

    private final IBootcampPersistencePort bootcampPersistencePort;
    private final ICapacityPersistencePort capacityPersistencePort;

    public BootcampUseCase(IBootcampPersistencePort bootcampPersistencePort, ICapacityPersistencePort capacityPersistencePort) {
        this.bootcampPersistencePort = bootcampPersistencePort;
        this.capacityPersistencePort = capacityPersistencePort;
    }

    @Override
    public Mono<Void> saveBootcamp(Bootcamp bootcamp) {
        return Mono.justOrEmpty(bootcamp)
                .filter(cap -> cap.getName() != null && cap.getName().length() <= 50)
                .switchIfEmpty(Mono.error(new DomainException(DomainConstants.NAME_MUST_BE_LESS_THAN_50_CHARACTERS)))
                .filter(cap -> cap.getDescription() != null && cap.getDescription().length() <= 90)
                .switchIfEmpty(Mono.error(new DomainException(DomainConstants.DESCRIPTION_MUST_BE_LESS_THAN_90_CHARACTERS)))
                .filter(cap -> cap.getCapacities() != null && !cap.getCapacities().isEmpty())
                .switchIfEmpty(Mono.error(new DomainException(DomainConstants.EMPTY_CAPACITY_LIST)))
                .filter(cap -> cap.getCapacities().size() >= DomainConstants.MIN_TECHNOLOGIES && cap.getCapacities().size() <= DomainConstants.MAX_TECHNOLOGIES)
                .switchIfEmpty(Mono.error(new DomainException(DomainConstants.INVALID_CAPACITY_COUNT)))
                .flatMap(validCap -> bootcampPersistencePort.findBootcampByName(validCap.getName()))
                .flatMap(existingCap -> Mono.error(new DomainException(DomainConstants.BOOTCAMP_ALREADY_EXISTS)))
                .switchIfEmpty(Mono.defer(() -> bootcampPersistencePort.saveBootcamp(bootcamp))
                        .flatMap(savedCap ->
                                capacityPersistencePort.saveCapacitiesBootcamp(savedCap.getId(), bootcamp.getCapacities())
                        )
                )
                .then();
    }

    @Override
    public Flux<Bootcamp> listBootcamps(Integer page, Integer size, String direction, String field) {
        return Mono.just(new PaginationParams(page, size, direction, field))
                .filter(params -> params.getPage() >= 0 && params.getSize() > 0)
                .switchIfEmpty(Mono.error(new DomainException(DomainConstants.INVALID_PAGINATION_PARAMS)))
                .filter(params -> params.getField().equals("name") || params.getField().equals("capacityCount"))
                .switchIfEmpty(Mono.error(new DomainException(DomainConstants.INVALID_SORT_FIELD)))
                .filter(params -> params.getDirection().equalsIgnoreCase("asc") || params.getDirection().equalsIgnoreCase("desc"))
                .switchIfEmpty(Mono.error(new DomainException(DomainConstants.INVALID_SORT_DIRECTION)))
                .flatMapMany(validParams ->
                        bootcampPersistencePort.listBootcamps(validParams.getPage(), validParams.getSize(), validParams.getDirection(), validParams.getField())
                                .flatMap(capacity ->
                                        capacityPersistencePort.findCapacitiesByBootcamp(capacity.getId())
                                                .collectList()
                                                .map(capacities -> {
                                                    capacity.setCapacityList(capacities);
                                                    return capacity;
                                                })
                                )
                );
    }


}