package org.joboffers.domain.offers;

class OfferNotFoundException extends RuntimeException{
    OfferNotFoundException(String message) {
        super(message);
    }
}
