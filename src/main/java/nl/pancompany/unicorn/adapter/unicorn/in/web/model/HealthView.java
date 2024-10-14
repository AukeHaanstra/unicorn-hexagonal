package nl.pancompany.unicorn.adapter.unicorn.in.web.model;

import nl.pancompany.unicorn.application.unicorn.port.out.GetFinancialHealthPort;
import nl.pancompany.unicorn.application.unicorn.domain.model.Unicorn;

public record HealthView (Unicorn.PhysicalHealth physicalHealth, GetFinancialHealthPort.FinancialHealthDto.FinancialHealth financialHealth) {
}
