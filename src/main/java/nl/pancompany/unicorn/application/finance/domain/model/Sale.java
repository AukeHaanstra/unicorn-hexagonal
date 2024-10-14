package nl.pancompany.unicorn.application.finance.domain.model;

import lombok.Value;
import nl.pancompany.unicorn.application.finance.domain.model.Customer.CustomerId;
import nl.pancompany.unicorn.application.unicorn.domain.model.Unicorn.UnicornId;

import java.time.LocalDateTime;
import java.util.UUID;

public record Sale(SaleId saleId, UnicornId unicornId,
                   CustomerId customerId, LocalDateTime saleDate, long price) {
    @Value(staticConstructor = "of")
    public static class SaleId {

        UUID value;

        public static SaleId generate() {
            return new SaleId(UUID.randomUUID());
        }

        public static SaleId of(String uuid) {
            return new SaleId(UUID.fromString(uuid));
        }
    }
}