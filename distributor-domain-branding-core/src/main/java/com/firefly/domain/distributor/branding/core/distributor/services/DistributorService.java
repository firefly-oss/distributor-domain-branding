package com.firefly.domain.distributor.branding.core.distributor.services;

import com.firefly.domain.distributor.branding.core.distributor.commands.*;
import com.firefly.domain.distributor.branding.core.distributor.queries.ReviewBrandingAuditLogsQuery;
import com.firefly.transactional.core.SagaResult;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

public interface DistributorService {

    Mono<SagaResult> onboardDistributor(RegisterDistributorCommand command);
    
    Mono<SagaResult> reviseBranding(ReviseBrandingCommand command);
    
    Mono<SagaResult> setDefaultBranding(SetDefaultBrandingCommand command);

    Mono<SagaResult> reviseTermsAndConditions(ReviseTermsAndConditionsCommand command);

}
