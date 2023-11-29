package nl.pancompany.unicorn.application.domain.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import nl.pancompany.unicorn.application.domain.model.Leg;
import nl.pancompany.unicorn.application.domain.model.Unicorn;
import nl.pancompany.unicorn.application.port.in.GetLegUsecase;
import nl.pancompany.unicorn.application.port.out.UnicornPersistencePort;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Service;

import static org.mapstruct.ReportingPolicy.ERROR;

@Service
@RequiredArgsConstructor
public class GetLegService implements GetLegUsecase {

    private final UnicornPersistencePort unicornUnicornPersistencePort;
    private final LegDtoMapper legDtoMapper;

    @Override
    public LegDto getLeg(@Valid QueryLegDto queryLegDto) {
        Unicorn unicorn = unicornUnicornPersistencePort.find(queryLegDto.unicornId());
        return legDtoMapper.map(unicorn, unicorn.getLeg(queryLegDto.legPosition()));
    }
    @Mapper(componentModel = "spring", unmappedTargetPolicy = ERROR)
    public interface LegDtoMapper {

        @Mapping(target = "unicornId", source = "unicorn.id")
        @Mapping(target = ".", source = "leg")
        LegDto map(Unicorn unicorn, Leg leg);
    }

}
