package nl.pancompany.unicorn.adapter.unicorn.out.persistence.database.repository;

import nl.pancompany.unicorn.adapter.unicorn.out.persistence.database.model.UnicornJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UnicornRepo extends JpaRepository<UnicornJpaEntity, Long> {

    Optional<UnicornJpaEntity> findByUnicornId(String unicornId);

    boolean existsByUnicornId(String unicornId);
}
