package com.firefly.domain.distributor.branding.core.distributor.handlers;

import com.firefly.common.domain.cqrs.annotations.CommandHandlerComponent;
import com.firefly.common.domain.cqrs.command.CommandHandler;
import com.firefly.core.distributor.sdk.api.DistributorApi;
import com.firefly.domain.distributor.branding.core.distributor.commands.RemoveDistributorInfoCommand;
import reactor.core.publisher.Mono;

@CommandHandlerComponent
public class RemoveDistributorInfoHandler extends CommandHandler<RemoveDistributorInfoCommand, Void> {

    private final DistributorApi distributorApi;

    public RemoveDistributorInfoHandler(DistributorApi distributorApi) {
        this.distributorApi = distributorApi;
    }

    @Override
    protected Mono<Void> doHandle(RemoveDistributorInfoCommand cmd) {
        return distributorApi.deleteDistributor(cmd.distributorId());
    }
}