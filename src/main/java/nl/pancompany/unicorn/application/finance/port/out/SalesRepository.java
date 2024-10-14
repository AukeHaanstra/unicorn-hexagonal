package nl.pancompany.unicorn.application.finance.port.out;

import nl.pancompany.unicorn.application.finance.domain.model.Customer;
import nl.pancompany.unicorn.application.finance.domain.model.Sale;
import nl.pancompany.unicorn.application.unicorn.domain.model.Unicorn;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public interface SalesRepository {

    default List<Sale> findAllSales(Unicorn.UnicornId unicornId) {
        List<Sale> sales = new ArrayList<>();
        Unicorn.UnicornId stubUnicornId = Unicorn.UnicornId.of("ffffffff-ffff-ffff-ffff-ffffffffffff");
        for (int i = 0; i < 6; i++) {
            Sale.SaleId saleId = Sale.SaleId.generate();
            Customer.CustomerId customerId = Customer.CustomerId.generate();
            sales.add(new Sale(saleId, stubUnicornId, customerId, LocalDateTime.now(), 1000L * (i + 1)));
        }
        return sales;
    }
}
