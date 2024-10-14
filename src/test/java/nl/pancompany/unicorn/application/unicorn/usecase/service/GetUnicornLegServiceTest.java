package nl.pancompany.unicorn.application.unicorn.usecase.service;

import nl.pancompany.unicorn.application.unicorn.port.in.GetLegUsecase;
import nl.pancompany.unicorn.application.unicorn.port.in.UpdateLegUsecase;
import nl.pancompany.unicorn.application.unicorn.port.out.UnicornRepositoryPort;
import nl.pancompany.unicorn.application.unicorn.domain.model.Leg;
import nl.pancompany.unicorn.application.unicorn.domain.model.Unicorn;
import nl.pancompany.unicorn.application.unicorn.usecase.mapper.LegDtoMapper;
import nl.pancompany.unicorn.application.unicorn.domain.model.Color;
import nl.pancompany.unicorn.application.unicorn.usecase.service.GetUnicornLegService;
import nl.pancompany.unicorn.application.unicorn.usecase.service.UpdateUnicornLegService;
import nl.pancompany.unicorn.testbuilders.UnicornTestBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

/**
 * Example of a common mockist-type test
 */
public class GetUnicornLegServiceTest {

    GetUnicornLegService getUnicornLegService;
    UpdateUnicornLegService updateUnicornLegService;
    UnicornRepositoryPort unicornRepositoryPort;
    LegDtoMapper legDtoMapper;

    @BeforeEach
    public void setup() {
        unicornRepositoryPort = mock(UnicornRepositoryPort.class);
        legDtoMapper = mock(LegDtoMapper.class);
        getUnicornLegService = new GetUnicornLegService(unicornRepositoryPort, legDtoMapper);
        updateUnicornLegService = new UpdateUnicornLegService(unicornRepositoryPort, legDtoMapper);
    }

    @Test
    public void canGetLeg() {
        var unicornId = Unicorn.UnicornId.generate();
        Unicorn unicorn = new UnicornTestBuilder().defaults().unicornId(unicornId).build();
        when(unicornRepositoryPort.find(unicornId)).thenReturn(unicorn);
        var legDto = new Leg.LegDto(Leg.LegPosition.FRONT_LEFT, Color.CYAN, Leg.LegSize.SMALL);
        when(legDtoMapper.map(new Leg(Leg.LegPosition.FRONT_LEFT, Color.CYAN, Leg.LegSize.SMALL))).thenReturn(legDto);
        var queryLegDto = new GetLegUsecase.QueryLegDto(unicornId, Leg.LegPosition.FRONT_LEFT);

        Leg.LegDto returnedLegDto = getUnicornLegService.getLeg(queryLegDto);

        verify(unicornRepositoryPort).find(unicornId);
        assertThat(returnedLegDto).isEqualTo(legDto);
    }

    @Test
    public void canUpdateLeg() {
        var unicornId = Unicorn.UnicornId.generate();
        Unicorn unicorn = new UnicornTestBuilder().defaults().unicornId(unicornId)
                .withLeg(new Leg(Leg.LegPosition.FRONT_LEFT, Color.PURPLE, Leg.LegSize.SMALL)).build();
        when(unicornRepositoryPort.find(unicornId)).thenReturn(unicorn);
        Unicorn patchedUnicorn = new UnicornTestBuilder().defaults().unicornId(unicornId)
                .withLeg(new Leg(Leg.LegPosition.FRONT_LEFT, Color.AUBURN, Leg.LegSize.SMALL)).build();
        when(unicornRepositoryPort.update(patchedUnicorn)).thenReturn(patchedUnicorn);
        when(legDtoMapper.map(new Leg(Leg.LegPosition.FRONT_LEFT, Color.AUBURN, Leg.LegSize.SMALL)))
                .thenReturn(new Leg.LegDto(Leg.LegPosition.FRONT_LEFT, Color.AUBURN, Leg.LegSize.SMALL));
        var updateLegDto = new UpdateLegUsecase.UpdateLegDto(unicornId, Leg.LegPosition.FRONT_LEFT, Color.AUBURN, Leg.LegSize.SMALL);

        Leg.LegDto updatedLegDto = updateUnicornLegService.updateLeg(updateLegDto);

        assertThat(updatedLegDto.color()).isEqualTo(Color.AUBURN);
    }
}
