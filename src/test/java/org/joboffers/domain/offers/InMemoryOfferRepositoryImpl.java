package org.joboffers.domain.offers;


import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

class InMemoryOfferRepositoryImpl implements OffersRepository {
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
    public Optional<Offer> findOfferByOfferId(String offerId) {
        return Optional.ofNullable(database.get(offerId));
    }


    @Override
    public <S extends Offer> List<S> saveAll(Iterable<S> entities) {
        List<S> savedOffers = new ArrayList<>();
        for (S entity : entities) {
            S savedEntity = (S) save(entity);
            savedOffers.add(savedEntity);
        }
        return savedOffers;
    }

    @Override
    public Optional<Offer> findById(String s) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(String s) {
        return false;
    }

    @Override
    public List<Offer> findAll() {
        List<Offer> allOffers = database.values().stream().toList();
        return allOffers;
    }

    @Override
    public List<Offer> findAllById(Iterable<String> strings) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(String s) {

    }

    @Override
    public void delete(Offer entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends String> strings) {

    }

    @Override
    public void deleteAll(Iterable<? extends Offer> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public boolean existsByOfferUrl(String url) {
        return database.values().stream().anyMatch(offer -> offer.offerUrl().equals(url));
    }

    @Override
    public <S extends Offer> S insert(S entity) {
        return null;
    }

    @Override
    public <S extends Offer> List<S> insert(Iterable<S> entities) {
        return null;
    }

    @Override
    public <S extends Offer> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Offer> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Offer> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Offer> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Offer> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Offer> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Offer, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public List<Offer> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Offer> findAll(Pageable pageable) {
        return null;
    }
}
