package nl.pancompany.unicorn.context.finance.application;

import lombok.Getter;
import nl.pancompany.unicorn.application.finance.port.out.SalesRepository;
import nl.pancompany.unicorn.application.finance.usecase.service.SalesService;

@Getter
public class FinanceContext {

    private final SalesService salesService;

    public FinanceContext() {
        this.salesService = new SalesService(new SalesRepository() {});
    }
}
