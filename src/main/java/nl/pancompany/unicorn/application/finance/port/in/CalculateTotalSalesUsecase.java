package nl.pancompany.unicorn.application.finance.port.in;

import nl.pancompany.unicorn.application.unicorn.domain.model.Unicorn;

public interface CalculateTotalSalesUsecase {

    TotalSalesDto calculateTotalSales(Unicorn.UnicornId unicornId);

    record TotalSalesDto(Unicorn.UnicornId unicornId, long salesTotal) {
    }
}
