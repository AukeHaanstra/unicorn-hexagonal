package nl.pancompany.unicorn;

import nl.pancompany.unicorn.application.domain.model.Leg;
import nl.pancompany.unicorn.application.domain.model.Unicorn;
import nl.pancompany.unicorn.application.domain.model.Unicorn.UnicornId;
import nl.pancompany.unicorn.application.port.out.UnicornPersistencePort;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

import static nl.pancompany.unicorn.application.domain.model.Color.*;
import static nl.pancompany.unicorn.application.domain.model.Leg.LegPosition.*;
import static nl.pancompany.unicorn.application.domain.model.Leg.LegSize.LARGE;
import static nl.pancompany.unicorn.application.domain.model.Leg.LegSize.SMALL;
import static org.assertj.core.api.Assertions.assertThat;

abstract class PersistenceTest {

    @Autowired
    UnicornPersistencePort unicornPersistencePort;

    @Test
    void savesAndFindsUnicornWithFourLegs() {
        var leg1 = new Leg(FRONT_LEFT, CHARCOAL, SMALL);
        var leg2 = new Leg(FRONT_RIGHT, PERIWINKLE, SMALL);
        var leg3 = new Leg(BACK_LEFT, MINT, SMALL);
        var leg4 = new Leg(BACK_RIGHT, PEACH, SMALL);
        var fullSetOfLegs = Set.of(leg1, leg2, leg3, leg4);
        var unicorn = new Unicorn(null, "Prancey Dazzlepuff", fullSetOfLegs);
        var savedUnicorn = unicornPersistencePort.save(unicorn);

        var foundUnicorn = unicornPersistencePort.find(savedUnicorn.id());
        assertThat(foundUnicorn.legs()).hasSize(4);
        assertThat(foundUnicorn.name()).isEqualTo("Prancey Dazzlepuff");
    }

    @Test
    void saveWithExistingIdMergesWithPersistedEntity() {
        var leg1_1 = new Leg(FRONT_LEFT, CYAN, SMALL);
        var leg1_2 = new Leg(FRONT_RIGHT, AQUA, SMALL);
        var leg1_3 = new Leg(BACK_LEFT, LIME, SMALL);
        var leg1_4 = new Leg(BACK_RIGHT, PINK, SMALL);
        var unicorn1 = new Unicorn(UnicornId.of(null), "Rainbow Jinglehorn",
                Set.of(leg1_1, leg1_2, leg1_3, leg1_4));
        var savedUnicorn1 = unicornPersistencePort.save(unicorn1);

        var leg2_1 = new Leg(FRONT_LEFT, BROWN, LARGE);
        var leg2_2 = new Leg(FRONT_RIGHT, CHOCOLATE, LARGE);
        var leg2_3 = new Leg(BACK_LEFT, EMERALD, LARGE);
        var leg2_4 = new Leg(BACK_RIGHT, SAPPHIRE, LARGE);
        var unicorn2 = new Unicorn(savedUnicorn1.id(), "Twinkletoes McFluff",
                Set.of(leg2_1, leg2_2, leg2_3, leg2_4));
        var savedUnicorn2 = unicornPersistencePort.save(unicorn2);

        var foundUnicorn = unicornPersistencePort.find(savedUnicorn2.id());
        assertThat(foundUnicorn.legs()).hasSize(4);
        assertThat(foundUnicorn.name()).isEqualTo("Twinkletoes McFluff");
    }
}
