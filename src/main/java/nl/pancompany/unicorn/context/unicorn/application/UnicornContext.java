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
import nl.pancompany.unicorn.common.Dao;

@Getter
public class UnicornContext {

    private final GetUnicornUsecase getUnicornService;
    private final GetLegUsecase getLegUsecase;
    private final UpdateLegUsecase updateLegUsecase;

    public UnicornContext(Dao<Unicorn, Unicorn.UnicornId> unicornDao, GetFinancialHealthPort getFinancialHealthPort) {
        this.getUnicornService = createUnicornService(unicornDao, getFinancialHealthPort);
        this.getLegUsecase = createGetLegService(unicornDao);
        this.updateLegUsecase = createUpdateLegService(unicornDao);
    }

    private GetUnicornService createUnicornService(Dao<Unicorn, Unicorn.UnicornId> unicornDao, GetFinancialHealthPort getFinancialHealthPort) {
        UnicornEnrichmentService unicornEnrichmentService = new UnicornEnrichmentService(getFinancialHealthPort,
                UnicornDtoMapper.INSTANCE);
        return new GetUnicornService(unicornDao, unicornEnrichmentService);
    }

    private GetUnicornLegService createGetLegService(Dao<Unicorn, Unicorn.UnicornId> unicornDao) {
        return new GetUnicornLegService(unicornDao, LegDtoMapper.INSTANCE);
    }

    private UpdateLegUsecase createUpdateLegService(Dao<Unicorn, Unicorn.UnicornId> unicornDao) {
        return new UpdateUnicornLegService(unicornDao, LegDtoMapper.INSTANCE);
    }
}
