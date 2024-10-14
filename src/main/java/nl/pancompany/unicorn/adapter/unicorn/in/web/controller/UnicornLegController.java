package nl.pancompany.unicorn.adapter.unicorn.in.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.pancompany.unicorn.application.unicorn.domain.model.Leg;
import nl.pancompany.unicorn.application.unicorn.domain.model.Leg.LegPosition;
import nl.pancompany.unicorn.application.unicorn.domain.model.Unicorn.UnicornId;
import nl.pancompany.unicorn.application.unicorn.port.in.GetLegUsecase;
import nl.pancompany.unicorn.adapter.unicorn.in.web.mapper.LegViewMapper;
import nl.pancompany.unicorn.adapter.unicorn.in.web.model.LegView;
import nl.pancompany.unicorn.application.unicorn.port.in.UpdateLegUsecase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/unicorns/{unicornId}/legs/{legPosition}")
public class UnicornLegController {

    private final LegViewMapper legViewMapper;
    private final GetLegUsecase getLegUsecase;
    private final UpdateLegUsecase updateLegUsecase;
    private final ObjectMapper objectMapper;

    @GetMapping
    public ResponseEntity<LegView> getLeg(@PathVariable("unicornId") String unicornId,
                                          @PathVariable("legPosition") LegPosition legPosition) {
        Leg.LegDto leg = getLegUsecase.getLeg(new GetLegUsecase.QueryLegDto(UnicornId.of(unicornId), legPosition));
        return ResponseEntity.ok(legViewMapper.map(leg));
    }

    @PatchMapping
    public ResponseEntity<LegView> patchLeg(@PathVariable("unicornId") String unicornId,
                                      @PathVariable("legPosition") LegPosition legPosition,
                                      @RequestBody JsonPatch jsonPatch)
            throws JsonPatchException, JsonProcessingException {
        Leg.LegDto legDto = getLegUsecase.getLeg(new GetLegUsecase.QueryLegDto(UnicornId.of(unicornId), legPosition));
        LegView legView = patch(legViewMapper.map(legDto), jsonPatch);
        LegView updatedLegView = legViewMapper.map(updateLegUsecase.updateLeg(legViewMapper.map(legView, unicornId)));
        return ResponseEntity.ok(updatedLegView);
    }

    private LegView patch(LegView leg, JsonPatch patch)
            throws JsonProcessingException, JsonPatchException {
        JsonNode patched = patch.apply(objectMapper.convertValue(leg, JsonNode.class));
        return objectMapper.treeToValue(patched, LegView.class);
    }
}
