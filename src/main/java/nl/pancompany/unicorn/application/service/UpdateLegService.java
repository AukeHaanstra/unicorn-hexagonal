package nl.pancompany.unicorn.application.domain.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.pancompany.unicorn.application.domain.model.Leg;
import nl.pancompany.unicorn.application.domain.model.Unicorn;
import nl.pancompany.unicorn.application.port.in.UpdateLegUsecase;
import nl.pancompany.unicorn.application.port.out.UnicornPersistencePort;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Service;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;
import static org.mapstruct.ReportingPolicy.ERROR;

@Service
@Slf4j
@RequiredArgsConstructor
public class UpdateLegService implements UpdateLegUsecase {

    private final UnicornPersistencePort unicornUnicornPersistencePort;
    private final UpdateLegDtoMapper updateLegDtoMapper;

    @Override
    public void updateLeg(@Valid UpdateLegDto updateLegDto) {
        Unicorn unicorn = unicornUnicornPersistencePort.find(updateLegDto.unicornId());
        Leg leg = unicorn.getLeg(updateLegDto.legPosition());
        updateLegDtoMapper.updateLeg(leg, updateLegDto);
        unicornUnicornPersistencePort.save(unicorn);
        log.info("Updated {} leg of unicorn {} (id={}) to color={} and size={}", leg.getLegPosition(), unicorn.name(),
                unicorn.id().value(), leg.getColor(), leg.getLegSize());
    }

    @Mapper(componentModel = "spring", unmappedTargetPolicy = ERROR, nullValuePropertyMappingStrategy = IGNORE)
    public interface UpdateLegDtoMapper {

        void updateLeg(@MappingTarget Leg leg, UpdateLegDto legPatchDto);
    }

}
