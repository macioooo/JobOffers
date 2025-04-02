package org.joboffers.domain.offers;

enum OfferMessages {
    OFFER_NOT_FOUND("Offer not found."),
    COULDNT_RETRIEVE_OFFERS("Couldn't retrieve offers");

    final String message;

    OfferMessages(String message) {
        this.message = message;
    }
}
