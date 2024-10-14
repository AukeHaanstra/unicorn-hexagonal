package nl.pancompany.unicorn.context.unicorn.adapter;

import lombok.Getter;
import nl.pancompany.unicorn.adapter.unicorn.out.persistence.inmemory.dao.UnicornJpaMapper;
import nl.pancompany.unicorn.application.unicorn.port.out.UnicornRepositoryPort;
import nl.pancompany.unicorn.adapter.unicorn.out.persistence.inmemory.dao.InMemoryUnicornDao;

@Getter
public class InMemoryPersistenceContext {

    private final UnicornRepositoryPort unicornRepositoryPort;

    public InMemoryPersistenceContext() {
        this.unicornRepositoryPort = new InMemoryUnicornDao(UnicornJpaMapper.INSTANCE);
    }
}
