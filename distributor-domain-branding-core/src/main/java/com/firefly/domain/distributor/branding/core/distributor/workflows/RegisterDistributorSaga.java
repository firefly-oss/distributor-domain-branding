package com.firefly.domain.distributor.branding.core.distributor.workflows;

import com.firefly.common.domain.cqrs.command.CommandBus;
import com.firefly.domain.distributor.branding.core.distributor.commands.RegisterDistributorInfoCommand;
import com.firefly.domain.distributor.branding.core.distributor.commands.RemoveDistributorInfoCommand;
import com.firefly.transactional.annotations.Saga;
import com.firefly.transactional.annotations.SagaStep;
import com.firefly.transactional.annotations.StepEvent;
import com.firefly.transactional.core.SagaContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

import static com.firefly.domain.distributor.branding.core.utils.constants.DistributorConstants.*;
import static com.firefly.domain.distributor.branding.core.utils.constants.GlobalConstants.CTX_DISTRIBUTOR_ID;


@Saga(name = SAGA_REGISTER_DISTRIBUTOR)
@Service
public class RegisterDistributorSaga {

    private final CommandBus commandBus;

    @Autowired
    public RegisterDistributorSaga(CommandBus commandBus) {
        this.commandBus = commandBus;
    }


    @SagaStep(id = STEP_REGISTER_DISTRIBUTOR, compensate = COMPENSATE_REMOVE_DISTRIBUTOR)
    @StepEvent(type = EVENT_DISTRIBUTOR_REGISTERED)
    public Mono<UUID> registerDistributor(RegisterDistributorInfoCommand cmd, SagaContext ctx) {
        return commandBus.send(cmd)
                .doOnNext(distributorId -> ctx.variables().put(CTX_DISTRIBUTOR_ID, distributorId));
    }

    public Mono<Void> removeDistributor(UUID distributorId) {
        return commandBus.send(new RemoveDistributorInfoCommand(distributorId));
    }


}
