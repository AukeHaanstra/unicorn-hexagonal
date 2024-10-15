package nl.pancompany.unicorn;

import nl.pancompany.unicorn.application.unicorn.port.in.GetLegUsecase;
import nl.pancompany.unicorn.application.unicorn.port.in.GetUnicornUsecase;
import nl.pancompany.unicorn.application.unicorn.port.in.UpdateLegUsecase;
import nl.pancompany.unicorn.common.Repository;
import nl.pancompany.unicorn.application.unicorn.domain.model.Unicorn;
import nl.pancompany.unicorn.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

    @Bean
    public ApplicationContext applicationContext(Repository<Unicorn, Unicorn.UnicornId> unicornRepository) {
        return new ApplicationContext(unicornRepository);
    }

    @Bean
    public GetUnicornUsecase getUnicornUsecase(ApplicationContext applicationContext) {
        return applicationContext.getGetUnicornUsecase();
    }

    @Bean
    public GetLegUsecase getLegUsecase(ApplicationContext applicationContext) {
        return applicationContext.getGetLegUsecase();
    }

    @Bean
    public UpdateLegUsecase updateLegUsecase(ApplicationContext applicationContext) {
        return applicationContext.getUpdateLegUsecase();
    }

}
