package org.joboffers.domain.offers;

import org.joboffers.domain.offers.dto.JobOffersResponse;

import java.util.List;

interface OffersFetcher {

    List<JobOffersResponse> fetchOffers();
}
