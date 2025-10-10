package com.firefly.domain.distributor.branding.core.distributor.handlers;

import com.firefly.common.cqrs.annotations.CommandHandlerComponent;
import com.firefly.common.cqrs.command.CommandHandler;
import com.firefly.core.distributor.sdk.api.DistributorBrandingApi;
import com.firefly.domain.distributor.branding.core.distributor.commands.RemoveBrandingCommand;
import reactor.core.publisher.Mono;

@CommandHandlerComponent
public class RemoveBrandingHandler extends CommandHandler<RemoveBrandingCommand, Void> {

    private final DistributorBrandingApi distributorBrandingApi;

    public RemoveBrandingHandler(DistributorBrandingApi distributorBrandingApi) {
        this.distributorBrandingApi = distributorBrandingApi;
    }

    @Override
    protected Mono<Void> doHandle(RemoveBrandingCommand cmd) {
        return distributorBrandingApi.deleteDistributorBranding(cmd.distributorId(), cmd.brandingId());
    }
}

