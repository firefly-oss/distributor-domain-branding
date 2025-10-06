package com.firefly.domain.distributor.branding.web.controller;

import com.firefly.domain.distributor.branding.core.distributor.commands.RegisterDistributorCommand;
import com.firefly.domain.distributor.branding.core.distributor.services.DistributorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/distributors")
@RequiredArgsConstructor
@Tag(name = "Distributor", description = "CQ queries and registration for Distributors")
public class DistributorController {

    private final DistributorService distributorService;

    @Operation(summary = "Onboard Distributor", description = "Onboard a new distributor with branding and terms & conditions.")
    @PostMapping
    public Mono<ResponseEntity<Object>> onboardDistributor(@Valid @RequestBody RegisterDistributorCommand command) {
        return distributorService.onboardDistributor(command)
                .thenReturn(ResponseEntity.ok().build());
    }


}
