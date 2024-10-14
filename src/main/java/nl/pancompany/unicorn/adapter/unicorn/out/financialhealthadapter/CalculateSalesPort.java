package nl.pancompany.unicorn.adapter.unicorn.out.financialhealthadapter;

import nl.pancompany.unicorn.application.finance.port.in.CalculateTotalSalesUsecase;
import nl.pancompany.unicorn.application.unicorn.domain.model.Unicorn;

public interface CalculateSalesPort {

    CalculateTotalSalesUsecase.TotalSalesDto calculateTotalSales(Unicorn.UnicornId unicornId);

}
