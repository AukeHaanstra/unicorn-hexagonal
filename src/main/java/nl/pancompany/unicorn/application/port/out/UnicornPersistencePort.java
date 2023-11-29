package nl.pancompany.unicorn.application.port.out;

import nl.pancompany.unicorn.application.domain.model.Unicorn;
import nl.pancompany.unicorn.application.domain.model.Unicorn.UnicornId;
import nl.pancompany.unicorn.application.exception.UnicornNotFoundException;

public interface UnicornPersistencePort {
    Unicorn find(UnicornId id) throws UnicornNotFoundException;

    Unicorn save(Unicorn entity);

    long count();
}
