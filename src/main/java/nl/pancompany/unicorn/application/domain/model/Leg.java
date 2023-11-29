package nl.pancompany.unicorn.application.domain.model;

import lombok.*;

@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
public class Leg {

        private final LegPosition legPosition;

        @Setter
        private Color color;

        @Setter
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
}
