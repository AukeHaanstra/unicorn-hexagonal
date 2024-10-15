package nl.pancompany.unicorn.context.unicorn.application;

import lombok.Getter;
import nl.pancompany.unicorn.application.unicorn.domain.model.Unicorn;
import nl.pancompany.unicorn.application.unicorn.domain.service.UnicornEnrichmentService;
import nl.pancompany.unicorn.application.unicorn.usecase.mapper.LegDtoMapper;
import nl.pancompany.unicorn.application.unicorn.usecase.mapper.UnicornDtoMapper;
import nl.pancompany.unicorn.application.unicorn.port.in.GetLegUsecase;
import nl.pancompany.unicorn.application.unicorn.port.in.GetUnicornUsecase;
import nl.pancompany.unicorn.application.unicorn.port.in.UpdateLegUsecase;
import nl.pancompany.unicorn.application.unicorn.port.out.GetFinancialHealthPort;
import nl.pancompany.unicorn.application.unicorn.usecase.service.GetUnicornLegService;
import nl.pancompany.unicorn.application.unicorn.usecase.service.GetUnicornService;
import nl.pancompany.unicorn.application.unicorn.usecase.service.UpdateUnicornLegService;
import nl.pancompany.unicorn.common.Repository;

@Getter
public class UnicornContext {

    private final GetUnicornUsecase getUnicornService;
    private final GetLegUsecase getLegUsecase;
    private final UpdateLegUsecase updateLegUsecase;

    public UnicornContext(Repository<Unicorn, Unicorn.UnicornId> unicornRepository, GetFinancialHealthPort getFinancialHealthPort) {
        this.getUnicornService = createUnicornService(unicornRepository, getFinancialHealthPort);
        this.getLegUsecase = createGetLegService(unicornRepository);
        this.updateLegUsecase = createUpdateLegService(unicornRepository);
    }

    private GetUnicornService createUnicornService(Repository<Unicorn, Unicorn.UnicornId> unicornRepository, GetFinancialHealthPort getFinancialHealthPort) {
        UnicornEnrichmentService unicornEnrichmentService = new UnicornEnrichmentService(getFinancialHealthPort,
                UnicornDtoMapper.INSTANCE);
        return new GetUnicornService(unicornRepository, unicornEnrichmentService);
    }

    private GetUnicornLegService createGetLegService(Repository<Unicorn, Unicorn.UnicornId> unicornRepository) {
        return new GetUnicornLegService(unicornRepository, LegDtoMapper.INSTANCE);
    }

    private UpdateLegUsecase createUpdateLegService(Repository<Unicorn, Unicorn.UnicornId> unicornRepository) {
        return new UpdateUnicornLegService(unicornRepository, LegDtoMapper.INSTANCE);
    }
}
