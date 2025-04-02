package org.joboffers.domain.offers;

import java.util.List;
import java.util.Optional;

interface OffersRepository {

    Offer save(Offer offer);
    List<Offer> saveAll(List<Offer> offers);
    Optional<Offer> retrieveOfferById(String offerId);
    List<Offer> retrieveAllOffers();

    boolean checkIfOfferIsAlreadyInDatabaseByUrl(String offerUrl);
}
