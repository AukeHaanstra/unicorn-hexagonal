package nl.pancompany.unicorn.adapter.unicorn.out.persistence.database;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import nl.pancompany.unicorn.adapter.unicorn.out.persistence.database.model.UnicornJpaEntity;
import nl.pancompany.unicorn.application.unicorn.domain.model.Unicorn;
import nl.pancompany.unicorn.application.unicorn.domain.model.Unicorn.UnicornId;
import nl.pancompany.unicorn.application.unicorn.port.out.UnicornRepository;
import nl.pancompany.unicorn.application.unicorn.usecase.exception.UnicornAlreadyExistsException;
import nl.pancompany.unicorn.application.unicorn.usecase.exception.UnicornNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Transactional
public class DatabaseUnicornRepository implements UnicornRepository {

    private final UnicornJpaRepository unicornJpaRepository;
    private final UnicornJpaMapper mapper;

    @Override
    public Unicorn find(UnicornId unicornId) throws UnicornNotFoundException {
        requireNonNull(unicornId);
        UnicornJpaEntity unicorn = unicornJpaRepository.findByUnicornId(unicornId.toStringValue())
                .orElseThrow(UnicornNotFoundException::new);
        return mapper.map(unicorn);
    }

    @Override
    public Unicorn add(Unicorn unicorn) {
        requireNonNull(unicorn);
        if (unicornJpaRepository.existsByUnicornId(unicorn.getUnicornId().toStringValue())) {
            throw new UnicornAlreadyExistsException();
        }
        return mapper.map(unicornJpaRepository.save(mapper.map(unicorn)));
    }

    @Override
    public Unicorn update(Unicorn unicorn) {
        requireNonNull(unicorn);
        UnicornJpaEntity persistedUnicorn = unicornJpaRepository.findByUnicornId(unicorn.getUnicornId().toStringValue())
                .orElseThrow(UnicornNotFoundException::new);
        UnicornJpaEntity unicornToMerge = mapper.map(unicorn);
        unicornToMerge.setId(persistedUnicorn.getId());
        return mapper.map(unicornJpaRepository.save(unicornToMerge));
    }

    @Override
    public long count() {
        return unicornJpaRepository.count();
    }

    private static void requireNonNull(Object object) {
        if (object == null) {
            throw new IllegalArgumentException("null");
        }
    }
}
