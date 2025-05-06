package org.joboffers.domain.offers;

import org.joboffers.domain.offers.dto.JobOffersResponse;

import java.util.List;

public interface OffersFetchable {

    List<JobOffersResponse> fetchOffers();
}
