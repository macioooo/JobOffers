package org.joboffers.infrastructure.offers.controllers;


import lombok.AllArgsConstructor;
import org.joboffers.domain.offers.OffersFacade;
import org.joboffers.domain.offers.dto.JobOffersResponse;
import org.joboffers.domain.offers.dto.OfferResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class OffersController {
    private final OffersFacade offersFacade;
    @GetMapping("/offers")
public ResponseEntity<List<OfferResponseDto>> offersResponse() {
        final var response = offersFacade.findAllOffers();
        return ResponseEntity.ok(response);
    }
}
