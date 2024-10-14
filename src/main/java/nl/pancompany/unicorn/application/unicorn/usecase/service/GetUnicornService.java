package nl.pancompany.unicorn.application.unicorn.usecase.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.pancompany.unicorn.application.unicorn.port.in.GetUnicornUsecase;
import nl.pancompany.unicorn.common.Dao;
import nl.pancompany.unicorn.application.unicorn.domain.model.Unicorn;
import nl.pancompany.unicorn.application.unicorn.domain.model.Unicorn.UnicornId;
import nl.pancompany.unicorn.application.unicorn.domain.service.UnicornEnrichmentService;
import nl.pancompany.unicorn.application.unicorn.usecase.exception.UnicornNotFoundException;

@Slf4j
@RequiredArgsConstructor
public class GetUnicornService implements GetUnicornUsecase {

    private final Dao<Unicorn, UnicornId> unicornDao;
    private final UnicornEnrichmentService unicornEnrichmentService;

    @Override
    public Unicorn.UnicornDto getUnicorn(UnicornId unicornId) throws UnicornNotFoundException {
        Unicorn unicorn = unicornDao.find(unicornId);
        return unicornEnrichmentService.enrich(unicorn);
    }

}
