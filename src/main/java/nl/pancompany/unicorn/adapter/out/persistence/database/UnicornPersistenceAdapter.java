package nl.pancompany.unicorn.adapter.out.persistence.database;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import nl.pancompany.unicorn.application.domain.model.Unicorn;
import nl.pancompany.unicorn.adapter.out.persistence.database.model.UnicornJpaEntity;
import nl.pancompany.unicorn.application.domain.model.Unicorn.UnicornId;
import nl.pancompany.unicorn.application.port.out.UnicornPersistencePort;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
@Profile("!test")
@RequiredArgsConstructor
@Transactional
public class UnicornPersistenceAdapter implements UnicornPersistencePort {

    private final UnicornRepo unicornRepo;
    private final UnicornJpaMapper mapper;

    @Override
    public Unicorn find(UnicornId unicornId) {
        UnicornJpaEntity unicorn = unicornRepo.findById(unicornId.value()).orElseThrow(EntityNotFoundException::new);
        return mapper.map(unicorn);
    }

    @Override
    public Unicorn save(Unicorn unicorn) {
        return mapper.map(unicornRepo.save(mapper.map(unicorn)));
    }

    @Override
    public long count() {
        return unicornRepo.count();
    }

    interface UnicornRepo extends JpaRepository<UnicornJpaEntity, Long> {
    }

}