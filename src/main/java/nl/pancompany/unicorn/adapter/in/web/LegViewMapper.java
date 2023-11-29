package nl.pancompany.unicorn.adapter.in.web;

import nl.pancompany.unicorn.adapter.in.web.model.LegView;
import nl.pancompany.unicorn.application.domain.model.Unicorn;
import nl.pancompany.unicorn.application.port.in.GetLegUsecase.LegDto;
import org.mapstruct.Mapper;

import static org.mapstruct.ReportingPolicy.ERROR;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ERROR)
interface LegViewMapper {

    LegView map(LegDto legDto);

    default Long map(Unicorn.UnicornId unicornId) {
        return unicornId.value();
    }
}
