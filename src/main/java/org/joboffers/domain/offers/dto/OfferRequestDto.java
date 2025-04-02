package org.joboffers.domain.offers.dto;

import lombok.Builder;

@Builder
public record OfferRequestDto(String role, String company,  String salary, String offerUrl) {
}
