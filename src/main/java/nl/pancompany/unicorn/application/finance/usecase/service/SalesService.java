package nl.pancompany.unicorn.application.finance.usecase.service;

import lombok.RequiredArgsConstructor;
import nl.pancompany.unicorn.application.finance.domain.model.Sale;
import nl.pancompany.unicorn.application.finance.port.in.CalculateTotalSalesUsecase;
import nl.pancompany.unicorn.application.finance.port.out.SalesRepository;
import nl.pancompany.unicorn.application.unicorn.domain.model.Unicorn.UnicornId;

@RequiredArgsConstructor
public class SalesService implements CalculateTotalSalesUsecase {

    private final SalesRepository salesRepository;

    @Override
    public TotalSalesDto calculateTotalSales(UnicornId unicornId) {
        long total = salesRepository.findAllSales(unicornId).stream()
                .filter(sale -> sale.unicornId().equals(unicornId))
                .mapToLong(Sale::price)
                .sum();
        return new TotalSalesDto(unicornId, total);
    }
}