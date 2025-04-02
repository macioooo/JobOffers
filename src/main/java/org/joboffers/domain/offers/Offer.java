package org.joboffers.domain.offers;

import lombok.Builder;

@Builder
public record Offer(String offerId, String role,String company, String salary, String offerUrl) {
}
