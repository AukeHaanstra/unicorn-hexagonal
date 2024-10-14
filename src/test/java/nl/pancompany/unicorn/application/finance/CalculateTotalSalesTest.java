package nl.pancompany.unicorn.application.finance;

import nl.pancompany.unicorn.application.ApplicationTestContext;
import nl.pancompany.unicorn.application.finance.port.in.CalculateTotalSalesUsecase;
import nl.pancompany.unicorn.application.finance.port.in.CalculateTotalSalesUsecase.SalesDto;
import nl.pancompany.unicorn.application.unicorn.domain.model.Unicorn;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CalculateTotalSalesTest {

    CalculateTotalSalesUsecase calculateTotalSalesUsecase;

    @BeforeEach
    void setup() {
        ApplicationTestContext applicationTestContext = new ApplicationTestContext();
        calculateTotalSalesUsecase = applicationTestContext.getCalculateTotalSalesUsecase();
    }

    @Test
    public void calculatesTotalSales() {
        SalesDto salesDto = calculateTotalSalesUsecase.calculateTotalSales(Unicorn.UnicornId.of("ffffffff-ffff-ffff-ffff-ffffffffffff"));

        assertThat(salesDto.salesTotal()).isEqualTo(21000);
    }


}
