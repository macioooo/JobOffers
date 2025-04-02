package org.joboffers.domain.offers.dto;

import lombok.Builder;

@Builder
public record OfferResponseDto(String offerId, String role, String company, String salary, String offerUrl) {
}
