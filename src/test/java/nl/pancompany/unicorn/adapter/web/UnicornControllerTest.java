package nl.pancompany.unicorn.adapter.web;

import nl.pancompany.unicorn.adapter.unicorn.in.web.mapper.LegViewMapperImpl;
import nl.pancompany.unicorn.adapter.unicorn.in.web.mapper.UnicornViewMapperImpl;
import nl.pancompany.unicorn.application.unicorn.domain.model.Unicorn;
import nl.pancompany.unicorn.application.unicorn.domain.model.Unicorn.UnicornId;
import nl.pancompany.unicorn.application.unicorn.usecase.service.GetUnicornService;
import nl.pancompany.unicorn.testbuilders.UnicornDtoTestBuilder;
import nl.pancompany.unicorn.adapter.unicorn.in.web.controller.UnicornController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UnicornController.class)
@Import({UnicornViewMapperImpl.class, LegViewMapperImpl.class})
class UnicornControllerTest {

    static final String NIL_UUID = "00000000-0000-0000-0000-000000000000";

    @Autowired
    MockMvc mockMvc;

    @MockBean
    GetUnicornService getUnicornService;

    final UnicornId unicornId = UnicornId.of(NIL_UUID);

    @Test
    void getUnicornGeneratesViewOnUnicorn() throws Exception {
        Unicorn.UnicornDto outputDto = new UnicornDtoTestBuilder().defaults().unicornId(unicornId).build();
        when(getUnicornService.getUnicorn(unicornId)).thenReturn(outputDto);

        mockMvc.perform(get("/unicorns/{unicornId}", unicornId.toStringValue()))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                            "unicornId": "00000000-0000-0000-0000-000000000000",
                            "name": "Rainbow Jinglehorn",
                            "legs": [{
                                    "legPosition": "BACK_RIGHT",
                                    "color": "PINK",
                                    "legSize": "SMALL"
                                }, {
                                    "legPosition": "FRONT_RIGHT",
                                    "color": "AQUA",
                                    "legSize": "SMALL"
                                }, {
                                    "legPosition": "BACK_LEFT",
                                    "color": "LIME",
                                    "legSize": "SMALL"
                                }, {
                                    "legPosition": "FRONT_LEFT",
                                    "color": "CYAN",
                                    "legSize": "SMALL"
                                }
                            ]
                        }
                        """));
    }

}
