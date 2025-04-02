package org.joboffers.domain.offers;

import org.joboffers.domain.offers.dto.JobOffersResponse;

import java.util.List;

class OffersFacadeTestConfiguration {

    private final OffersFetcher fetcher;
    private final OffersRepository repository;

    OffersFacadeTestConfiguration() {
        this.fetcher = new OffersFetcherTestImpl(
                List.of(
                        new JobOffersResponse("Java developer", "ABC", "1000", "offerone.pl/offer/1"),
                        new JobOffersResponse("Python developer", "DEF", "3000", "offertwo.pl"),
                        new JobOffersResponse("PHP developer", "GHI", "15000", "offerthree.pl"),
                        new JobOffersResponse("Scala developer", "JKL", "12500", "offerfour.pl"),
                        new JobOffersResponse("Vibe coder", "ABC", "99000", "offerone.pl/offer/2")

                )
        );
        this.repository = new InMemoryOfferRepositoryImpl();
    }

    OffersFacadeTestConfiguration(OffersFetcher fetcher) {
        this.fetcher = fetcher;
        this.repository = new InMemoryOfferRepositoryImpl();
    }

    OffersFacade createFacadeForTests(){
        OffersFetchingService service = new OffersFetchingService(this.repository, this.fetcher);
        return new OffersFacade(this.repository, service);
    }
}
