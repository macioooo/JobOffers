package org.joboffers.domain.offers;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
interface OffersRepository extends MongoRepository<Offer, String> {

    Optional<Offer> findOfferByOfferId(String offerId);

    boolean existsByOfferUrl(String offerUrl);
}
