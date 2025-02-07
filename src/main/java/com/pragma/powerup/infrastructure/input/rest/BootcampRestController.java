package com.pragma.powerup.infrastructure.input.rest;

import com.pragma.powerup.application.dto.request.BootcampPageRequestDto;
import com.pragma.powerup.application.dto.request.BootcampRequestDto;
import com.pragma.powerup.application.dto.response.BootcampResponseDto;
import com.pragma.powerup.application.handler.IBootcampHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/bootcamp")
@RequiredArgsConstructor
public class BootcampRestController {

    private final IBootcampHandler bootcampHandler;

    @Operation(summary = "Add a new bootcamp")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Bootcamp created", content = @Content),
            @ApiResponse(responseCode = "409", description = "Bootcamp already exists", content = @Content)
    })
    @PostMapping("/")
    public Mono<Void> saveCapacity(@Valid @RequestBody BootcampRequestDto bootcampRequestDto) {
        return bootcampHandler.saveBootcamp(bootcampRequestDto).then();
    }

    @Operation(summary = "List of bootcamps paginated by name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of bootcamps", content = @Content)
    })
    @PostMapping("/list")
    public Flux<BootcampResponseDto> listCapacities(@Valid @RequestBody BootcampPageRequestDto bootcampPageRequestDto) {
        return bootcampHandler.listBootcamps(bootcampPageRequestDto);
    }

}