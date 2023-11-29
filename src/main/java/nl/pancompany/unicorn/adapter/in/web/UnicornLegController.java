package nl.pancompany.unicorn.adapter.in.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.pancompany.unicorn.adapter.in.web.model.LegView;
import nl.pancompany.unicorn.application.domain.model.Leg.LegPosition;
import nl.pancompany.unicorn.application.port.in.GetLegUsecase;
import nl.pancompany.unicorn.application.port.in.GetLegUsecase.LegDto;
import nl.pancompany.unicorn.application.port.in.GetLegUsecase.QueryLegDto;
import nl.pancompany.unicorn.application.port.in.UpdateLegUsecase;
import nl.pancompany.unicorn.application.port.in.UpdateLegUsecase.UpdateLegDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UnicornLegController {

    private final LegViewMapper legViewMapper;
    private final GetLegUsecase getLegUsecase;
    private final UpdateLegUsecase updateLegUsecase;
    private final ObjectMapper objectMapper;

    @GetMapping(path = "/unicorns/{unicornId}/legs/{legPosition}")
    public ResponseEntity<LegView> getLeg(@PathVariable("unicornId") Long unicornId,
                                          @PathVariable("legPosition") LegPosition legPosition) {
        LegDto leg = getLegUsecase.getLeg(new QueryLegDto(unicornId, legPosition));
        return ResponseEntity.ok(legViewMapper.map(leg));
    }

    @PatchMapping(path = "/unicorns/{unicornId}/legs/{legPosition}")
    public ResponseEntity<?> patchLeg(@PathVariable("unicornId") Long unicornId,
                                      @PathVariable("legPosition") LegPosition legPosition,
                                      @RequestBody JsonPatch jsonPatch)
            throws JsonPatchException, JsonProcessingException {
        UpdateLegDto updateLegDto = enrichWithPatch(new UpdateLegDto(unicornId, legPosition), jsonPatch);
        updateLegUsecase.updateLeg(updateLegDto);
        return ResponseEntity.noContent().build();
    }

    private UpdateLegDto enrichWithPatch(UpdateLegDto leg, JsonPatch patch)
            throws JsonProcessingException, JsonPatchException {
        JsonNode patched = patch.apply(objectMapper.convertValue(leg, JsonNode.class));
        return objectMapper.treeToValue(patched, UpdateLegDto.class);
    }
}