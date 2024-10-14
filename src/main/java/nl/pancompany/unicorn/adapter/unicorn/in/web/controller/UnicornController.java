package nl.pancompany.unicorn.adapter.unicorn.in.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.pancompany.unicorn.application.unicorn.domain.model.Unicorn;
import nl.pancompany.unicorn.application.unicorn.port.in.GetUnicornUsecase;
import nl.pancompany.unicorn.adapter.unicorn.in.web.mapper.UnicornViewMapper;
import nl.pancompany.unicorn.adapter.unicorn.in.web.model.UnicornView;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/unicorns/{unicornId}")
public class UnicornController {

    private final GetUnicornUsecase getUnicornUsecase;
    private final UnicornViewMapper unicornViewMapper;

    @GetMapping
    public ResponseEntity<UnicornView> getLeg(@PathVariable("unicornId") String unicornId) {
        Unicorn.UnicornDto unicorn = getUnicornUsecase.getUnicorn(Unicorn.UnicornId.of(unicornId));
        return ResponseEntity.ok(unicornViewMapper.map(unicorn));
    }

}
