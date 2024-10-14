package nl.pancompany.unicorn.application.unicorn.usecase.mapper;

import nl.pancompany.unicorn.application.unicorn.domain.model.Leg;
import org.mapstruct.Mapper;

import static org.mapstruct.ReportingPolicy.ERROR;
import static org.mapstruct.factory.Mappers.getMapper;

@Mapper(unmappedTargetPolicy = ERROR)
public interface LegDtoMapper {

    LegDtoMapper INSTANCE = getMapper(LegDtoMapper.class);

    Leg.LegDto map(Leg leg);
}
