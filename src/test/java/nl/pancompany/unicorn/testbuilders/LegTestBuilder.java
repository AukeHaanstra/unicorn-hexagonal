package nl.pancompany.unicorn.testbuilders;

import nl.pancompany.unicorn.application.unicorn.domain.model.Color;
import nl.pancompany.unicorn.application.unicorn.domain.model.Leg;
import nl.pancompany.unicorn.application.unicorn.domain.model.Leg.LegPosition;

import static nl.pancompany.unicorn.application.unicorn.domain.model.Color.BLUE;
import static nl.pancompany.unicorn.application.unicorn.domain.model.Leg.LegPosition.FRONT_LEFT;
import static nl.pancompany.unicorn.application.unicorn.domain.model.Leg.LegSize.LARGE;

public class LegTestBuilder {

    private LegPosition legPosition;

    private Color color;

    private Leg.LegSize legSize;

    public LegTestBuilder defaults() {
        legPosition = FRONT_LEFT;
        color = BLUE;
        legSize = LARGE;
        return this;
    }

    public LegTestBuilder legPosition(LegPosition legPosition) {
        this.legPosition = legPosition;
        return this;
    }

    public LegTestBuilder color(Color color) {
        this.color = color;
        return this;
    }

    public LegTestBuilder legSize(Leg.LegSize legSize) {
        this.legSize = legSize;
        return this;
    }

    public Leg build() {
        return new Leg(this.legPosition, this.color, this.legSize);
    }


}
