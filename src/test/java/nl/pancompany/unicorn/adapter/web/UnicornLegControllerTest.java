package nl.pancompany.unicorn.adapter.web;

import nl.pancompany.unicorn.adapter.unicorn.in.web.controller.UnicornLegController;
import nl.pancompany.unicorn.adapter.unicorn.in.web.mapper.LegViewMapperImpl;
import nl.pancompany.unicorn.application.unicorn.domain.model.Color;
import nl.pancompany.unicorn.application.unicorn.domain.model.Leg;
import nl.pancompany.unicorn.application.unicorn.domain.model.Leg.LegPosition;
import nl.pancompany.unicorn.application.unicorn.domain.model.Unicorn.UnicornId;
import nl.pancompany.unicorn.application.unicorn.usecase.exception.UnicornNotFoundException;
import nl.pancompany.unicorn.application.unicorn.port.in.GetLegUsecase;
import nl.pancompany.unicorn.application.unicorn.port.in.UpdateLegUsecase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static nl.pancompany.unicorn.application.unicorn.domain.model.Color.GREEN;
import static nl.pancompany.unicorn.application.unicorn.domain.model.Color.RED;
import static nl.pancompany.unicorn.application.unicorn.domain.model.Leg.LegSize.LARGE;
import static nl.pancompany.unicorn.application.unicorn.domain.model.Leg.LegSize.SMALL;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UnicornLegController.class)
@Import(LegViewMapperImpl.class)
class UnicornLegControllerTest {

    static final String NIL_UUID = "00000000-0000-0000-0000-000000000000";

    @Autowired
    MockMvc mockMvc;

    @MockBean
    GetLegUsecase getLegUsecase;

    @MockBean
    UpdateLegUsecase updateLegUsecase;

    final UnicornId unicornId = UnicornId.of(NIL_UUID);
    final LegPosition legPosition = LegPosition.FRONT_LEFT;

    final Leg.LegDto legDto = new Leg.LegDto(legPosition, RED, SMALL);

    @Test
    void getLegGeneratesViewOnLeg() throws Exception {
        GetLegUsecase.QueryLegDto expectedInputDto = new GetLegUsecase.QueryLegDto(unicornId, legPosition);
        Leg.LegDto outputDto = new Leg.LegDto(legPosition, Color.CYAN, SMALL);
        when(getLegUsecase.getLeg(expectedInputDto)).thenReturn(outputDto);

        mockMvc.perform(get("/unicorns/{unicornId}/legs/{legPosition}", unicornId.toStringValue(), legPosition.name()))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                            "legPosition": "FRONT_LEFT",
                            "color": "CYAN",
                            "legSize": "SMALL"
                        }
                        """));
    }

    @Test
    void getLegOfUnknownUnicornGenerates404() throws Exception {
        GetLegUsecase.QueryLegDto expectedInputDto = new GetLegUsecase.QueryLegDto(unicornId, legPosition);
        when(getLegUsecase.getLeg(expectedInputDto)).thenThrow(UnicornNotFoundException.class);

        mockMvc.perform(get("/unicorns/{unicornId}/legs/{legPosition}", unicornId.toStringValue(), legPosition.name()))
                .andExpect(status().isNotFound());
    }

    @Test
    void sendsColorUpdateCommandOnPatch() throws Exception {
        when(getLegUsecase.getLeg(new GetLegUsecase.QueryLegDto(unicornId, legPosition))).thenReturn(legDto);
        when(updateLegUsecase.updateLeg(new UpdateLegUsecase.UpdateLegDto(unicornId, legPosition, GREEN, SMALL))).thenReturn(new Leg.LegDto(legPosition, GREEN, SMALL));

        mockMvc.perform(patch("/unicorns/{unicornId}/legs/{legPosition}", unicornId.toStringValue(), legPosition.name())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                [{
                                    "op":"replace",
                                    "path":"/color",
                                    "value":"GREEN"
                                }]
                                """))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                            "legPosition": "FRONT_LEFT",
                            "color": "GREEN",
                            "legSize": "SMALL"
                        }
                        """));
        verify(updateLegUsecase).updateLeg(new UpdateLegUsecase.UpdateLegDto(unicornId, legPosition, GREEN, SMALL));
    }

    @Test
    void sendsLegSizeUpdateCommandOnPatch() throws Exception {
        when(getLegUsecase.getLeg(new GetLegUsecase.QueryLegDto(unicornId, legPosition))).thenReturn(legDto);
        when(updateLegUsecase.updateLeg(new UpdateLegUsecase.UpdateLegDto(unicornId, legPosition, RED, LARGE))).thenReturn(new Leg.LegDto(legPosition, RED, LARGE));

        mockMvc.perform(patch("/unicorns/{unicornId}/legs/{legPosition}", unicornId.toStringValue(), legPosition.name())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                [{
                                    "op":"replace",
                                    "path":"/legSize",
                                    "value":"LARGE"
                                }]
                                """))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                            "legPosition": "FRONT_LEFT",
                            "color": "RED",
                            "legSize": "LARGE"
                        }
                        """));
        verify(updateLegUsecase).updateLeg(new UpdateLegUsecase.UpdateLegDto(unicornId, legPosition, RED, LARGE));
    }

    @Test
    void sendsColorAndLegSizeUpdateCommandOnPatch() throws Exception {
        when(getLegUsecase.getLeg(new GetLegUsecase.QueryLegDto(unicornId, legPosition))).thenReturn(legDto);
        when(updateLegUsecase.updateLeg(new UpdateLegUsecase.UpdateLegDto(unicornId, legPosition, GREEN, LARGE))).thenReturn(new Leg.LegDto(legPosition, GREEN, LARGE));

        mockMvc.perform(patch("/unicorns/{unicornId}/legs/{legPosition}", unicornId.toStringValue(), legPosition.name())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                [{
                                    "op":"replace",
                                    "path":"/color",
                                    "value":"GREEN"
                                },{
                                    "op":"replace",
                                    "path":"/legSize",
                                    "value":"LARGE"
                                }]
                                """))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                            "legPosition": "FRONT_LEFT",
                            "color": "GREEN",
                            "legSize": "LARGE"
                        }
                        """));
        verify(updateLegUsecase).updateLeg(new UpdateLegUsecase.UpdateLegDto(unicornId, legPosition, GREEN, LARGE));
    }

    @Test
    void updateLegOfUnknownUnicornGenerates404() throws Exception {
        when(getLegUsecase.getLeg(new GetLegUsecase.QueryLegDto(unicornId, legPosition))).thenReturn(legDto);
        UpdateLegUsecase.UpdateLegDto expectedInputDto = new UpdateLegUsecase.UpdateLegDto(unicornId, legPosition, GREEN, SMALL);
        doThrow(UnicornNotFoundException.class).when(updateLegUsecase).updateLeg(expectedInputDto);

        mockMvc.perform(patch("/unicorns/{unicornId}/legs/{legPosition}", unicornId.toStringValue(), legPosition.name())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                [{
                                    "op":"replace",
                                    "path":"/color",
                                    "value":"GREEN"
                                }]
                                """))
                .andExpect(status().isNotFound());
    }
}
