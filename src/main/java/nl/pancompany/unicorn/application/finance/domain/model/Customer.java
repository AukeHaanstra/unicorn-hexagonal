package nl.pancompany.unicorn.application.finance.domain.model;

import lombok.*;

import java.util.UUID;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class Customer {
    @NonNull
    private final CustomerId customerId;

    @NonNull
    private final String name;

    @NonNull
    @Setter(AccessLevel.PACKAGE)
    private String contactDetails;

    @Value(staticConstructor = "of")
    public static class CustomerId {

        UUID value;

        public static CustomerId generate() {
            return new CustomerId(UUID.randomUUID());
        }

        public static CustomerId of(String uuid) {
            return new CustomerId(UUID.fromString(uuid));
        }
    }
}