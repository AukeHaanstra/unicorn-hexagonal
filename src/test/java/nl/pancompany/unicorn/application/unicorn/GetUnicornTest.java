package nl.pancompany.unicorn.application.unicorn;

import nl.pancompany.unicorn.application.ApplicationTestContext;
import nl.pancompany.unicorn.application.unicorn.port.in.GetUnicornUsecase;
import nl.pancompany.unicorn.application.unicorn.port.out.GetFinancialHealthPort;
import nl.pancompany.unicorn.application.unicorn.port.out.UnicornRepository;
import nl.pancompany.unicorn.application.unicorn.domain.model.Leg;
import nl.pancompany.unicorn.application.unicorn.domain.model.Unicorn;
import nl.pancompany.unicorn.application.unicorn.domain.model.Unicorn.UnicornId;
import nl.pancompany.unicorn.application.unicorn.usecase.exception.UnicornNotFoundException;
import nl.pancompany.unicorn.testbuilders.UnicornTestBuilder;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class GetUnicornTest {

    UnicornRepository unicornRepository;
    GetUnicornUsecase getUnicornUsecase;
    Unicorn savedUnicorn;

    @BeforeEach
    public void setup() {
        ApplicationTestContext applicationTestContext = new ApplicationTestContext();
        unicornRepository = applicationTestContext.getUnicornRepository();
        getUnicornUsecase = applicationTestContext.getGetUnicornUsecase();

        savedUnicorn = unicornRepository.add(new UnicornTestBuilder().defaults().build());
    }

    @Test
    void findsUnicorn() {
        Unicorn.UnicornDto unicornDto = getUnicornUsecase.getUnicorn(savedUnicorn.getUnicornId());

        Assertions.assertThat(unicornDto.unicornId()).isEqualTo(savedUnicorn.getUnicornId());
        assertThat(unicornDto.name()).isEqualTo(savedUnicorn.getName());
        for (Leg.LegDto leg : unicornDto.legs()) {
            Leg savedLeg = savedUnicorn.getLeg(leg.legPosition());
            Assertions.assertThat(leg.color()).isEqualTo(savedLeg.getColor());
            Assertions.assertThat(leg.legSize()).isEqualTo(savedLeg.getLegSize());
        }
    }

    @Test
    void exceptionOnGetBecauseUnicornNotFound() {
        UnicornId unknownUnicornId = UnicornId.of("00000000-0000-0000-0000-000000000000");
        assertThatThrownBy(() -> getUnicornUsecase.getUnicorn(unknownUnicornId)).isInstanceOf(UnicornNotFoundException.class);
    }

    @Test
    void findUnicornHealth() {
        unicornRepository.add(new UnicornTestBuilder().healthyDefaults().unicornId(UnicornId.of("ffffffff-ffff-ffff-ffff-ffffffffffff")).build());
        Unicorn.UnicornDto unicornDto = getUnicornUsecase.getUnicorn(UnicornId.of("ffffffff-ffff-ffff-ffff-ffffffffffff"));
        assertThat(unicornDto.health().getFinancialHealth()).isEqualTo(GetFinancialHealthPort.FinancialHealthDto.FinancialHealth.SUFFICIENT);
        assertThat(unicornDto.health().getPhysicalHealth()).isEqualTo(Unicorn.PhysicalHealth.MODERATE);
    }
}
