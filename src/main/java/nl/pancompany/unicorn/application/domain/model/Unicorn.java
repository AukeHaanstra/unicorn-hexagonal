package nl.pancompany.unicorn.application.domain.model;

import nl.pancompany.unicorn.application.domain.model.Leg.LegPosition;

import java.util.Set;

public record Unicorn(UnicornId id, String name, Set<Leg> legs) {
    public Unicorn {
        if (legs.size() != 4) {
            throw new IllegalArgumentException("Unicorns must have four legs.");
        }
    }

    public Leg getLeg(LegPosition legPosition) {
        return legs.stream()
                .filter(leg -> leg.getLegPosition().equals(legPosition))
                .findFirst()
                .orElseThrow(IllegalStateException::new);
    }
    public record UnicornId(Long value) {

        public static UnicornId of(Long value) {
            return new UnicornId(value);
        }
    }
}