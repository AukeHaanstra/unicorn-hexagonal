package nl.pancompany.unicorn.adapter.unicorn.out.financialhealthadapter;

import lombok.RequiredArgsConstructor;
import nl.pancompany.unicorn.application.finance.port.in.CalculateTotalSalesUsecase;
import nl.pancompany.unicorn.application.unicorn.port.out.GetFinancialHealthPort;
import nl.pancompany.unicorn.application.unicorn.domain.model.Unicorn;

@RequiredArgsConstructor
public class FinancialHealthAdapter implements GetFinancialHealthPort {

    private final CalculateSalesPort calculateSalesPort;
    private final FinancialHealthMapper mapper;

    public FinancialHealthDto getFinancialHealth(Unicorn.UnicornId unicornId) {
        CalculateTotalSalesUsecase.TotalSalesDto totalSalesDto = calculateSalesPort.calculateTotalSales(unicornId);
        return mapper.toFinancialHealthDto(totalSalesDto);
    }
}