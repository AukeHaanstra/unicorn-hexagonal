package nl.pancompany.unicorn.context.finance.adapter;

import lombok.Getter;
import nl.pancompany.unicorn.adapter.finance.in.CalculateSalesAdapter;
import nl.pancompany.unicorn.application.finance.usecase.service.SalesService;

@Getter
public class CalculateSalesAdapterContext {

    private final CalculateSalesAdapter calculateSalesAdapter;

    public CalculateSalesAdapterContext(SalesService salesService) {
        this.calculateSalesAdapter = new CalculateSalesAdapter(salesService);
    }
}
