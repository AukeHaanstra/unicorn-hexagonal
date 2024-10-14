package nl.pancompany.unicorn.testbuilders;

import nl.pancompany.unicorn.application.unicorn.domain.model.Leg;
import nl.pancompany.unicorn.application.unicorn.domain.model.Unicorn;
import nl.pancompany.unicorn.application.unicorn.domain.model.Unicorn.UnicornId;

import java.util.HashSet;
import java.util.Set;

import static nl.pancompany.unicorn.application.unicorn.domain.model.Color.*;
import static nl.pancompany.unicorn.application.unicorn.domain.model.Leg.LegPosition.*;
import static nl.pancompany.unicorn.application.unicorn.domain.model.Leg.LegSize.LARGE;
import static nl.pancompany.unicorn.application.unicorn.domain.model.Leg.LegSize.SMALL;

public class UnicornTestBuilder {

    private UnicornId unicornId;
    private String name;
    private Set<Leg> legs;

    public UnicornTestBuilder defaults() {
        name = "Rainbow Jinglehorn";
        legs = new HashSet<>();
        legs.add(new Leg(FRONT_LEFT, CYAN, SMALL));
        legs.add(new Leg(FRONT_RIGHT, AQUA, SMALL));
        legs.add(new Leg(BACK_LEFT, LIME, SMALL));
        legs.add(new Leg(BACK_RIGHT, PINK, SMALL));
        return this;
    }

    public UnicornTestBuilder healthyDefaults() {
        name = "Rainbow Jinglehorn";
        legs = new HashSet<>();
        legs.add(new Leg(FRONT_LEFT, CYAN, SMALL));
        legs.add(new Leg(FRONT_RIGHT, CYAN, LARGE));
        legs.add(new Leg(BACK_LEFT, RED, SMALL));
        legs.add(new Leg(BACK_RIGHT, CYAN, SMALL));
        return this;
    }

    public UnicornTestBuilder unicornId(UnicornId unicornId) {
        this.unicornId = unicornId;
        return this;
    }

    public UnicornTestBuilder name(String name) {
        this.name = name;
        return this;
    }

    public UnicornTestBuilder legs(Set<Leg> legs) {
        this.legs = legs;
        return this;
    }

    public UnicornTestBuilder withLeg(Leg newLeg) {
        Leg oldLeg = legs.stream()
                .filter(leg -> leg.getLegPosition().equals(newLeg.getLegPosition()))
                .findFirst().orElseThrow(IllegalStateException::new);
        legs.remove(oldLeg);
        legs.add(newLeg);
        return this;
    }

    public Unicorn build() {
        return new Unicorn(unicornId, name, legs);
    }
}
