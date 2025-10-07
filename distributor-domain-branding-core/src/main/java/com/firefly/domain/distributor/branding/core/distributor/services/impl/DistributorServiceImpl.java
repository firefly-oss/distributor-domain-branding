package com.firefly.domain.distributor.branding.core.distributor.services.impl;

import com.firefly.common.domain.cqrs.query.QueryBus;
import com.firefly.domain.distributor.branding.core.distributor.commands.RegisterDistributorCommand;
import com.firefly.domain.distributor.branding.core.distributor.services.DistributorService;
import com.firefly.domain.distributor.branding.core.distributor.workflows.RegisterDistributorSaga;
import com.firefly.transactional.core.SagaResult;
import com.firefly.transactional.engine.SagaEngine;
import com.firefly.transactional.engine.StepInputs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class DistributorServiceImpl implements DistributorService {

    private final SagaEngine engine;
    private final QueryBus queryBus;

    @Autowired
    public DistributorServiceImpl(SagaEngine engine, QueryBus queryBus){
        this.engine=engine;
        this.queryBus = queryBus;
    }

    @Override
    public Mono<SagaResult> onboardDistributor(RegisterDistributorCommand command) {
        StepInputs inputs = StepInputs.builder()
                .forStep(RegisterDistributorSaga::registerDistributor, command.getDistributorInfo())
                .forStep(RegisterDistributorSaga::registerTAndCTemplate, command.getTermsAndConditionsTemplate())
                .forStep(RegisterDistributorSaga::registerTermsAndConditions, command.getTermsAndConditions())
                .forStep(RegisterDistributorSaga::registerAuditLog, command.getAuditLog())
                .forStep(RegisterDistributorSaga::registerBranding, command.getBranding())

                .build();

        return engine.execute(RegisterDistributorSaga.class, inputs);
    }

}
