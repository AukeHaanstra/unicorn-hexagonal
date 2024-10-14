package nl.pancompany.unicorn.application.unicorn.domain.model;

import lombok.*;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class Leg {

        @NonNull
        private final LegPosition legPosition;

        @Setter(AccessLevel.PACKAGE)
        @NonNull
        private Color color;

        @Setter(AccessLevel.PACKAGE)
        @NonNull
        private LegSize legSize;

        @RequiredArgsConstructor
        @Getter
        public enum LegPosition {
                FRONT_LEFT("front left"), FRONT_RIGHT("front right"), BACK_LEFT("back left"), BACK_RIGHT("back right");

                private final String description;
        }

        @RequiredArgsConstructor
        @Getter
        public enum LegSize {
                LARGE("large"), SMALL("small");

                private final String description;
        }

        public record LegDto(LegPosition legPosition, Color color, LegSize legSize) {
        }
}
