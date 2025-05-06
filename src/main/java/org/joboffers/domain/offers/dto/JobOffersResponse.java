package org.joboffers.domain.offers.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record JobOffersResponse(@JsonProperty("title")String role, String company, String salary, String offerUrl) {
}
