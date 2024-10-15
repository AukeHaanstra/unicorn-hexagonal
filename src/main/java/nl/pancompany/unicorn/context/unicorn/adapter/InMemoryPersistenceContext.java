package nl.pancompany.unicorn.context.unicorn.adapter;

import lombok.Getter;
import nl.pancompany.unicorn.adapter.unicorn.out.persistence.inmemory.UnicornJpaMapper;
import nl.pancompany.unicorn.application.unicorn.port.out.UnicornRepository;
import nl.pancompany.unicorn.adapter.unicorn.out.persistence.inmemory.InMemoryUnicornRepository;

@Getter
public class InMemoryPersistenceContext {

    private final UnicornRepository unicornRepository;

    public InMemoryPersistenceContext() {
        this.unicornRepository = new InMemoryUnicornRepository(UnicornJpaMapper.INSTANCE);
    }
}
