package nl.pancompany.unicorn.adapter.unicorn.in.web.model;

import java.util.Set;

public record UnicornView(String unicornId, String name, Set<LegView> legs, HealthView health) {
}
