package org.joboffers.domain.offers;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Document
public record Offer(@Id String offerId,
                    String role,
                    String company,
                    String salary,
                    String offerUrl) {
}
