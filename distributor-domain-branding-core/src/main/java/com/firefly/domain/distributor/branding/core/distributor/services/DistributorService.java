package com.firefly.domain.distributor.branding.core.distributor.services;

import com.firefly.domain.distributor.branding.core.distributor.commands.RegisterDistributorCommand;
import com.firefly.transactional.core.SagaResult;
import reactor.core.publisher.Mono;

public interface DistributorService {

    Mono<SagaResult> onboardDistributor(RegisterDistributorCommand command);

}
