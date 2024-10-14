package nl.pancompany.unicorn.adapter.unicorn.in.web.mapper;

import nl.pancompany.unicorn.application.unicorn.domain.model.Leg;
import nl.pancompany.unicorn.application.unicorn.domain.model.Unicorn.UnicornId;
import nl.pancompany.unicorn.adapter.unicorn.in.web.model.LegView;
import nl.pancompany.unicorn.application.unicorn.port.in.UpdateLegUsecase;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import static org.mapstruct.ReportingPolicy.ERROR;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ERROR)
public interface LegViewMapper {

    LegView map(Leg.LegDto legDto);

    @Mapping(target = "unicornId", source = "unicornId", qualifiedByName = "mapUnicornId")
    UpdateLegUsecase.UpdateLegDto map(LegView legDto, String unicornId);

    @Named("mapUnicornId")
    default UnicornId mapUnicornId(String unicornId) {
        return UnicornId.of(unicornId);
    }

}
