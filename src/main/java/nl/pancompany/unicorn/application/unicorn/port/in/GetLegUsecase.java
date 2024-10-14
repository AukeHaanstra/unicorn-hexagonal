package nl.pancompany.unicorn.application.unicorn.port.in;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.constraints.NotNull;
import nl.pancompany.unicorn.application.unicorn.domain.model.Leg;
import nl.pancompany.unicorn.application.unicorn.domain.model.Unicorn;
import nl.pancompany.unicorn.application.unicorn.usecase.exception.UnicornNotFoundException;

public interface GetLegUsecase {

    Leg.LegDto getLeg(QueryLegDto queryLegDto) throws UnicornNotFoundException, ConstraintViolationException;

    record QueryLegDto(@NotNull Unicorn.UnicornId unicornId, @NotNull Leg.LegPosition legPosition) {
    }
}
