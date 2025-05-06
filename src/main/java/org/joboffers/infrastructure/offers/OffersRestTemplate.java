package org.joboffers.infrastructure.offers;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.joboffers.domain.offers.OffersFetchable;
import org.joboffers.domain.offers.dto.JobOffersResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
@Log4j2
@AllArgsConstructor
public class OffersRestTemplate implements OffersFetchable {

    private final RestTemplate restTemplate;
    private final String uri;
    private final int port;
    private final static String SERVICE = "/offers";

    @Override
    public List<JobOffersResponse> fetchOffers() {
        log.info("started fetching offers");
        HttpHeaders headers = new HttpHeaders();
        try {
            final String url = getUrlForService(SERVICE);
            final HttpEntity<HttpHeaders> requestEntity = new HttpEntity<>(headers);
            ResponseEntity<List<JobOffersResponse>> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    requestEntity,
                    new ParameterizedTypeReference<>() {
                    }
            );
            List<JobOffersResponse> responseBody = response.getBody();
            if (responseBody == null) {
                log.info("body was empty");
                return Collections.emptyList();
            }
            log.info("succesfully got response: " + responseBody);
            return responseBody;
        } catch (ResourceAccessException exception) {
            log.error("couldn't get body: " + exception);
            return Collections.emptyList();
        }
    }

    private String getUrlForService(String service) {
        return uri + ":" + port + service;
    }
}
