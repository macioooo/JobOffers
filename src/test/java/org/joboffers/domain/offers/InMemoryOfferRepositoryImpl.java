package org.joboffers.domain.offers;


import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

class InMemoryOfferRepositoryImpl implements OffersRepository{
    Map<String, Offer> database = new ConcurrentHashMap<>();
    @Override
    public Offer save(Offer offer) {
        if (database.values().stream().anyMatch(offerInDb -> offerInDb.offerUrl().equals(offer.offerUrl()))) {
            throw new DuplicateKeyException(String.format("Offer with url %s is already in database", offer.offerUrl()));
        }
        Offer savedOffer = Offer.builder()
                .offerId(UUID.randomUUID().toString())
                .role(offer.role())
                .company(offer.company())
                .offerUrl(offer.offerUrl())
                .salary(offer.salary())
                .build();
        database.put(savedOffer.offerId(), savedOffer);
        return savedOffer;
    }

    @Override
    public List<Offer> saveAll(List<Offer> offers) {
        List<Offer> savedOffers = offers.stream().map(offer -> save(offer)).collect(Collectors.toList());
        return savedOffers;

    }

    @Override
    public Optional<Offer> retrieveOfferById(String offerId) {
        return Optional.ofNullable(database.get(offerId));
    }

    @Override
    public List<Offer> retrieveAllOffers() {
        List<Offer> allOffers = database.values().stream().toList();
        return allOffers;
    }

    @Override
    public boolean checkIfOfferIsAlreadyInDatabaseByUrl(String url) {
        return database.values().stream().anyMatch(offer -> offer.offerUrl().equals(url));
    }
}
