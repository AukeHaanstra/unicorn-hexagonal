package nl.pancompany.unicorn.application.port.in;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import nl.pancompany.unicorn.application.domain.model.Color;
import nl.pancompany.unicorn.application.domain.model.Leg.LegPosition;
import nl.pancompany.unicorn.application.domain.model.Leg.LegSize;
import nl.pancompany.unicorn.application.domain.model.Unicorn.UnicornId;
import nl.pancompany.unicorn.application.exception.UnicornNotFoundException;

public interface UpdateLegUsecase {

    void updateLeg(@Valid UpdateLegDto updateLegDto) throws UnicornNotFoundException;

    record UpdateLegDto(@NotNull UnicornId unicornId, @NotNull LegPosition legPosition, Color color,
                               LegSize legSize) {

        public UpdateLegDto(@NotNull Long unicornId, @NotNull LegPosition legPosition) {
            this(UnicornId.of(unicornId), legPosition, null, null);
        }
    }

}
