package org.joboffers.domain.offers;

class DuplicateKeyException extends RuntimeException{
    DuplicateKeyException(String message) {
        super(message);
    }
}
