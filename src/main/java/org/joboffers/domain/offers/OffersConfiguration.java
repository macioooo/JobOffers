package org.joboffers.domain.offers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OffersConfiguration {


    @Bean
    OffersFacade offersFacade(OffersFetchable offersFetchable, OffersRepository offersRepository) {
        OffersFetchingService offersFetchingService = new OffersFetchingService(offersRepository, offersFetchable);
        return new OffersFacade(offersRepository, offersFetchingService);
    }

}
