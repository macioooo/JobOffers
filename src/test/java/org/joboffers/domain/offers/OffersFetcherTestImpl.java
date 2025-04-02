package org.joboffers.domain.offers;

import org.joboffers.domain.offers.dto.JobOffersResponse;

import java.util.List;

class OffersFetcherTestImpl implements OffersFetcher{
    List<JobOffersResponse> fetchedOffers;

    OffersFetcherTestImpl(List<JobOffersResponse> fetchedOffers) {
        this.fetchedOffers = fetchedOffers;
    }

    @Override
    public List<JobOffersResponse> fetchOffers() {
        return this.fetchedOffers;
    }
}
