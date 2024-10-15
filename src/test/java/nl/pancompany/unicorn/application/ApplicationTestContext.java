package nl.pancompany.unicorn.application;

import lombok.Getter;
import nl.pancompany.unicorn.application.finance.port.in.CalculateTotalSalesUsecase;
import nl.pancompany.unicorn.application.unicorn.port.in.GetLegUsecase;
import nl.pancompany.unicorn.application.unicorn.port.in.GetUnicornUsecase;
import nl.pancompany.unicorn.application.unicorn.port.in.UpdateLegUsecase;
import nl.pancompany.unicorn.application.unicorn.port.out.UnicornRepository;
import nl.pancompany.unicorn.context.ApplicationContext;
import nl.pancompany.unicorn.context.unicorn.adapter.InMemoryPersistenceContext;

@Getter
public class ApplicationTestContext {

    private final GetUnicornUsecase getUnicornUsecase;
    private final GetLegUsecase getLegUsecase;
    private final UpdateLegUsecase updateLegUsecase;
    private final UnicornRepository unicornRepository;
    private final CalculateTotalSalesUsecase calculateTotalSalesUsecase;

    public ApplicationTestContext() {
        InMemoryPersistenceContext inMemoryPersistenceContext = new InMemoryPersistenceContext();
        this.unicornRepository = inMemoryPersistenceContext.getUnicornRepository();
        ApplicationContext applicationContext = new ApplicationContext(unicornRepository);
        this.getUnicornUsecase = applicationContext.getGetUnicornUsecase();
        this.getLegUsecase = applicationContext.getGetLegUsecase();
        this.updateLegUsecase = applicationContext.getUpdateLegUsecase();
        this.calculateTotalSalesUsecase = applicationContext.getCalculateTotalSalesUsecase();
    }
}
