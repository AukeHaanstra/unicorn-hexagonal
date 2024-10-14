package nl.pancompany.unicorn.application.unicorn.port.out;

import nl.pancompany.unicorn.application.unicorn.domain.model.Unicorn.UnicornId;

public interface GetFinancialHealthPort {

    FinancialHealthDto getFinancialHealth(UnicornId unicornId);

    record FinancialHealthDto(UnicornId unicornId, FinancialHealth financialHealth) {

        public enum FinancialHealth {
            EXCELLENT, GOOD, SUFFICIENT, INSUFFICIENT, CRITICAL
        }
    }
}
