package nl.pancompany.unicorn.testbuilders;

import nl.pancompany.unicorn.application.unicorn.domain.model.Leg;
import nl.pancompany.unicorn.application.unicorn.domain.model.Unicorn;
import nl.pancompany.unicorn.application.unicorn.domain.model.Unicorn.UnicornId;

import java.util.HashSet;
import java.util.Set;

import static nl.pancompany.unicorn.application.unicorn.domain.model.Color.*;
import static nl.pancompany.unicorn.application.unicorn.domain.model.Leg.LegPosition.*;
import static nl.pancompany.unicorn.application.unicorn.domain.model.Leg.LegSize.SMALL;

public class UnicornDtoTestBuilder {

    private UnicornId unicornId;
    private String name;
    private Set<Leg.LegDto> legs;

    public UnicornDtoTestBuilder defaults() {
        name = "Rainbow Jinglehorn";
        legs = new HashSet<>();
        legs.add(new Leg.LegDto(FRONT_LEFT, CYAN, SMALL));
        legs.add(new Leg.LegDto(FRONT_RIGHT, AQUA, SMALL));
        legs.add(new Leg.LegDto(BACK_LEFT, LIME, SMALL));
        legs.add(new Leg.LegDto(BACK_RIGHT, PINK, SMALL));
        return this;
    }

    public UnicornDtoTestBuilder unicornId(UnicornId unicornId) {
        this.unicornId = unicornId;
        return this;
    }

    public UnicornDtoTestBuilder name(String name) {
        this.name = name;
        return this;
    }

    public UnicornDtoTestBuilder legs(Set<Leg.LegDto> legs) {
        this.legs = legs;
        return this;
    }

    public UnicornDtoTestBuilder withLeg(Leg.LegDto newLeg) {
        Leg.LegDto oldLeg = legs.stream()
                .filter(leg -> leg.legPosition().equals(newLeg.legPosition()))
                .findFirst().orElseThrow(IllegalStateException::new);
        legs.remove(oldLeg);
        legs.add(newLeg);
        return this;
    }

    public Unicorn.UnicornDto build() {
        return new Unicorn.UnicornDto(unicornId, name, legs, null);
    }
}
