package nl.pancompany.unicorn.context;

import lombok.Getter;
import nl.pancompany.unicorn.application.finance.port.in.CalculateTotalSalesUsecase;
import nl.pancompany.unicorn.application.unicorn.port.in.GetLegUsecase;
import nl.pancompany.unicorn.application.unicorn.port.in.GetUnicornUsecase;
import nl.pancompany.unicorn.application.unicorn.port.in.UpdateLegUsecase;
import nl.pancompany.unicorn.common.Dao;
import nl.pancompany.unicorn.application.unicorn.domain.model.Unicorn;
import nl.pancompany.unicorn.context.finance.adapter.CalculateSalesAdapterContext;
import nl.pancompany.unicorn.context.unicorn.adapter.FinanceAdapterContext;
import nl.pancompany.unicorn.context.finance.application.FinanceContext;
import nl.pancompany.unicorn.context.unicorn.application.UnicornContext;

@Getter
public class ApplicationContext {

    private final GetUnicornUsecase getUnicornUsecase;
    private final GetLegUsecase getLegUsecase;
    private final UpdateLegUsecase updateLegUsecase;
    private final CalculateTotalSalesUsecase calculateTotalSalesUsecase;

    public ApplicationContext(Dao<Unicorn, Unicorn.UnicornId> unicornDao) {
        FinanceContext financeContext = new FinanceContext();
        CalculateSalesAdapterContext calculateSalesAdapterContext = new CalculateSalesAdapterContext(financeContext.getSalesService());
        FinanceAdapterContext financeAdapterContext = new FinanceAdapterContext(calculateSalesAdapterContext.getCalculateSalesAdapter());
        UnicornContext unicornContext = new UnicornContext(unicornDao, financeAdapterContext.getGetFinancialHealthPort());
        this.getUnicornUsecase = unicornContext.getGetUnicornService();
        this.getLegUsecase = unicornContext.getGetLegUsecase();
        this.updateLegUsecase = unicornContext.getUpdateLegUsecase();
        this.calculateTotalSalesUsecase = financeContext.getSalesService();
    }
}
