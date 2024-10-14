package nl.pancompany.unicorn.application.unicorn.port.in;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.constraints.NotNull;
import nl.pancompany.unicorn.application.unicorn.domain.model.Color;
import nl.pancompany.unicorn.application.unicorn.domain.model.Leg;
import nl.pancompany.unicorn.application.unicorn.domain.model.Unicorn;
import nl.pancompany.unicorn.application.unicorn.usecase.exception.UnicornNotFoundException;

public interface UpdateLegUsecase {

    Leg.LegDto updateLeg(UpdateLegDto updateLegDto) throws UnicornNotFoundException, ConstraintViolationException;

    record UpdateLegDto(@NotNull Unicorn.UnicornId unicornId, @NotNull Leg.LegPosition legPosition, Color color,
                        Leg.LegSize legSize) {
    }
}
