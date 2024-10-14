package nl.pancompany.unicorn.application.unicorn.domain.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import nl.pancompany.unicorn.application.unicorn.port.out.GetFinancialHealthPort;
import nl.pancompany.unicorn.application.unicorn.port.out.GetFinancialHealthPort.FinancialHealthDto;
import nl.pancompany.unicorn.application.unicorn.port.out.GetFinancialHealthPort.FinancialHealthDto.FinancialHealth;
import nl.pancompany.unicorn.application.unicorn.domain.model.Unicorn;
import nl.pancompany.unicorn.application.unicorn.domain.model.Unicorn.PhysicalHealth;
import nl.pancompany.unicorn.application.unicorn.usecase.mapper.UnicornDtoMapper;

@RequiredArgsConstructor
public class UnicornEnrichmentService {

    private final GetFinancialHealthPort getFinancialHealthPort;
    private final UnicornDtoMapper unicornDtoMapper;

    public Unicorn.UnicornDto enrich(Unicorn unicorn) {
        FinancialHealthDto financialHealthDto = getFinancialHealthPort.getFinancialHealth(unicorn.getUnicornId());
        Unicorn.UnicornDto unicornDto = unicornDtoMapper.map(unicorn, financialHealthDto);
        unicornDto.health().setFinancialHealth(financialHealthDto.financialHealth()); // Workaround for Mapstruct bug
        return unicornDto;
    }

    @Data
    public static class HealthDto {

        private PhysicalHealth physicalHealth;
        private FinancialHealth financialHealth;
    }
}
