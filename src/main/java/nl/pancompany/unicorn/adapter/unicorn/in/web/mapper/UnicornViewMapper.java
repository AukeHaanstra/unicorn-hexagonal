package nl.pancompany.unicorn.adapter.unicorn.in.web.mapper;

import nl.pancompany.unicorn.application.unicorn.domain.model.Unicorn;
import nl.pancompany.unicorn.application.unicorn.domain.model.Unicorn.UnicornId;
import nl.pancompany.unicorn.adapter.unicorn.in.web.model.UnicornView;
import org.mapstruct.Mapper;

import static org.mapstruct.ReportingPolicy.ERROR;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ERROR, uses = LegViewMapper.class)
public interface UnicornViewMapper {

    UnicornView map(Unicorn.UnicornDto unicorn);

    default String mapUnicornId(UnicornId unicornId) {
        return unicornId.toStringValue();
    }
}
