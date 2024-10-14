package nl.pancompany.unicorn.application.unicorn.domain.model;

import lombok.*;
import nl.pancompany.unicorn.application.unicorn.domain.model.Leg.LegPosition;
import nl.pancompany.unicorn.application.unicorn.domain.model.Leg.LegSize;
import nl.pancompany.unicorn.application.unicorn.domain.service.UnicornEnrichmentService;
import nl.pancompany.unicorn.application.unicorn.usecase.util.ListUtils;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import static java.util.Objects.requireNonNullElseGet;
import static nl.pancompany.unicorn.application.unicorn.domain.model.Unicorn.PhysicalHealth.*;

@Getter
@ToString
@EqualsAndHashCode
public class Unicorn {

    private final UnicornId unicornId;
    private final String name;
    private final Set<Leg> legs;

    public Unicorn(UnicornId unicornId, @NonNull String name, @NonNull Set<Leg> legs) {
        if (legs.size() != 4) {
            throw new IllegalArgumentException("Unicorns must have four legs.");
        }
        this.unicornId = requireNonNullElseGet(unicornId, UnicornId::generate);
        this.name = name;
        this.legs = legs;
    }

    public Leg getLeg(LegPosition legPosition) {
        return legs.stream()
                .filter(leg -> leg.getLegPosition().equals(legPosition))
                .findFirst()
                .orElseThrow(IllegalStateException::new);
    }

    public void setLegColor(LegPosition legPosition, Color color) {
        Leg leg = getLeg(legPosition);
        leg.setColor(color);
    }

    public void setLegSize(LegPosition legPosition, LegSize legSize) {
        Leg leg = getLeg(legPosition);
        leg.setLegSize(legSize);
    }

    public PhysicalHealth getPhysicalHealth() {
        List<Color> legColors = legs.stream().map(Leg::getColor).toList();
        int abnormallyColoredLegs = (int) ListUtils.countDifferentFromMostFrequent(legColors);
        List<LegSize> legSizes = legs.stream().map(Leg::getLegSize).toList();
        int abnormallySizedLegs = (int) ListUtils.countDifferentFromMostFrequent(legSizes);

        int abnormalitiesDetected = abnormallyColoredLegs + abnormallySizedLegs; // max 3+3=6

        return switch ((Integer) abnormalitiesDetected) {
            case 0 -> EXCELLENT;
            case 1 -> GOOD;
            case 2, 3 -> MODERATE;
            case 4 -> POOR;
            case Integer l when l >= 5L -> CRITICAL;
            default -> throw new IllegalStateException("Unexpected value: " + abnormalitiesDetected);
        };
    }

    public enum PhysicalHealth {
        EXCELLENT, GOOD, MODERATE, POOR, CRITICAL
    }

    @Value(staticConstructor = "of")
    public static class UnicornId {

        UUID value;

        public String toStringValue() {
            return value.toString();
        }

        public static UnicornId generate() {
            return new UnicornId(UUID.randomUUID());
        }

        public static UnicornId of(String uuid) {
            return new UnicornId(UUID.fromString(uuid));
        }
    }

    public record UnicornDto(UnicornId unicornId, String name, Set<Leg.LegDto> legs,
                                    UnicornEnrichmentService.HealthDto health) {
    }
}