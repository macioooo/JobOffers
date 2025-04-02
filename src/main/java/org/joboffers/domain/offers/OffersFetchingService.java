package org.joboffers.domain.offers;

import lombok.AllArgsConstructor;
import org.joboffers.domain.offers.dto.JobOffersResponse;

import java.util.List;
import static org.joboffers.domain.offers.OfferMapper.mapFromListOfJobOffersResponseToOfferList;

@AllArgsConstructor
class OffersFetchingService {
    private final OffersRepository repository;
    private final OffersFetcher fetcher;

    List<Offer> fetchAllOffersAndSaveWhenNotInDatabase() {
        List<JobOffersResponse> jobOffersResponses = fetcher.fetchOffers();
        List<JobOffersResponse> filteredJobOffersResponses = filterAllOffersAlreadyInDatabase(jobOffersResponses);
        List<Offer> mappedOffers = mapFromListOfJobOffersResponseToOfferList(filteredJobOffersResponses);
        List<Offer> savedOffers = repository.saveAll(mappedOffers);
        return savedOffers;
    }

    private List<JobOffersResponse> filterAllOffersAlreadyInDatabase(List<JobOffersResponse> offers) {
        return offers.stream()
                .filter(offer -> !repository.checkIfOfferIsAlreadyInDatabaseByUrl(offer.offerUrl()))
                .toList();
    }
}
