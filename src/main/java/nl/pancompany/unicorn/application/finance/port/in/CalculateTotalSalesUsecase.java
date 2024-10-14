package nl.pancompany.unicorn.application.finance.port.in;

import nl.pancompany.unicorn.application.unicorn.domain.model.Unicorn;

public interface CalculateTotalSalesUsecase {

    SalesDto calculateTotalSales(Unicorn.UnicornId unicornId);

    record SalesDto(Unicorn.UnicornId unicornId, long salesTotal) {
    }
}
