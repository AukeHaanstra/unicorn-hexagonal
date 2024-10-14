package nl.pancompany.unicorn.application.unicorn.port.in;

import nl.pancompany.unicorn.application.unicorn.domain.model.Unicorn;
import nl.pancompany.unicorn.application.unicorn.usecase.exception.UnicornNotFoundException;

public interface GetUnicornUsecase {

    Unicorn.UnicornDto getUnicorn(Unicorn.UnicornId unicornId) throws UnicornNotFoundException;

}
