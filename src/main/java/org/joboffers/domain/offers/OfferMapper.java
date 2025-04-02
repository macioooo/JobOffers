package org.joboffers.domain.offers;

import org.joboffers.domain.offers.dto.JobOffersResponse;
import org.joboffers.domain.offers.dto.OfferResponseDto;
import org.joboffers.domain.offers.dto.OfferRequestDto;

import java.util.List;

class OfferMapper {
    static OfferResponseDto mapFromOfferToDto(Offer offer) {
        return OfferResponseDto.builder()
                .offerId(offer.offerId())
                .role(offer.role())
                .salary(offer.salary())
                .offerUrl(offer.offerUrl())
                .build();
    }

    static Offer mapFromRequestToOffer(OfferRequestDto offerRequestDto) {
        return Offer.builder()
                .offerUrl(offerRequestDto.offerUrl())
                .salary(offerRequestDto.salary())
                .role(offerRequestDto.role())
                .build();
    }

    static Offer mapFromJobOffersResponseToOffer(JobOffersResponse jobOffersResponse) {
        return Offer.builder()
                .company(jobOffersResponse.company())
                .role(jobOffersResponse.role())
                .salary(jobOffersResponse.salary())
                .offerUrl(jobOffersResponse.offerUrl())
                .build();
    }

    static List<Offer> mapFromListOfJobOffersResponseToOfferList(List<JobOffersResponse> jobOffersResponses) {
        return jobOffersResponses.stream().map(OfferMapper::mapFromJobOffersResponseToOffer).toList();
    }

    static List<OfferResponseDto> mapFromListToDtoList(List<Offer> offers) {
        return offers.stream().map(OfferMapper::mapFromOfferToDto).toList();
    }
}
