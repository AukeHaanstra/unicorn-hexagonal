package nl.pancompany.unicorn.adapter.finance.in;

import lombok.RequiredArgsConstructor;
import nl.pancompany.unicorn.adapter.unicorn.out.financialhealthadapter.CalculateSalesPort;
import nl.pancompany.unicorn.application.finance.port.in.CalculateTotalSalesUsecase;
import nl.pancompany.unicorn.application.unicorn.domain.model.Unicorn;

@RequiredArgsConstructor
public class CalculateSalesAdapter implements CalculateSalesPort {

    private final CalculateTotalSalesUsecase calculateTotalSalesUsecase;

    @Override
    public CalculateTotalSalesUsecase.SalesDto calculateTotalSales(Unicorn.UnicornId unicornId) {
        return calculateTotalSalesUsecase.calculateTotalSales(unicornId);
    }
}
