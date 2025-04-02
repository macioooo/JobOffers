package org.joboffers.domain.offers;

import lombok.AllArgsConstructor;
import org.joboffers.domain.offers.dto.OfferRequestDto;
import org.joboffers.domain.offers.dto.OfferResponseDto;

import java.util.List;

import static org.joboffers.domain.offers.OfferMapper.*;
import static org.joboffers.domain.offers.OfferMessages.OFFER_NOT_FOUND;

@AllArgsConstructor
public class OffersFacade {

    private final OffersRepository repository;
    private final OffersFetchingService offersFetchingService;

    public List<OfferResponseDto> findAllOffers() {
        return mapFromListToDtoList(repository.retrieveAllOffers());
    }

    public OfferResponseDto findOfferById(String offerId) {
        return repository.retrieveOfferById(offerId)
                .map(offer -> mapFromOfferToDto(offer))
                .orElseThrow(() -> new OfferNotFoundException(OFFER_NOT_FOUND.message));
    }


    public OfferResponseDto saveOffer(OfferRequestDto offerRequestDto) {
        Offer offer = mapFromRequestToOffer(offerRequestDto);
        Offer savedOffer = repository.save(offer);
        return mapFromOfferToDto(savedOffer);
    }

    public List<OfferResponseDto> fetchAllOffersAndSaveIfNotExists() {
        return mapFromListToDtoList(offersFetchingService.fetchAllOffersAndSaveWhenNotInDatabase());
    }
}
