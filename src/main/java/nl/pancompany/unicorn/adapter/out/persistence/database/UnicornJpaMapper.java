package nl.pancompany.unicorn.adapter.out.persistence.database;

import nl.pancompany.unicorn.adapter.out.persistence.database.model.UnicornJpaEntity;
import nl.pancompany.unicorn.adapter.out.persistence.database.model.UnicornLegJpaEntity;
import nl.pancompany.unicorn.application.domain.model.Leg;
import nl.pancompany.unicorn.application.domain.model.Unicorn;
import nl.pancompany.unicorn.application.domain.model.Unicorn.UnicornId;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR,
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED)
public interface UnicornJpaMapper {

    Unicorn map(UnicornJpaEntity unicornJpaEntity);

    default UnicornId mapUnicornId(Long value) {
        return UnicornId.of(value);
    }

    UnicornJpaEntity map(Unicorn unicorn);

    default Long map(UnicornId unicornId) {
        return unicornId == null ? null : unicornId.value();
    }

    @Mapping(target = "unicorn", ignore = true)
    @Mapping(target = "id", ignore = true)
    UnicornLegJpaEntity map(Leg leg);

}