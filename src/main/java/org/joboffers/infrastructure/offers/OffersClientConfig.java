package org.joboffers.infrastructure.offers;

import org.joboffers.domain.offers.OffersFetchable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class OffersClientConfig {
    @Bean
    public RestTemplateErrorsResponseHandler offersErrorsResponseHandler() {
        return new RestTemplateErrorsResponseHandler();
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateErrorsResponseHandler restTemplateErrorsResponseHandler) {
        return new RestTemplateBuilder()
                .errorHandler(restTemplateErrorsResponseHandler)
                .setReadTimeout(Duration.ofMillis(7000))
                .setConnectTimeout(Duration.ofMillis(7000))
                .build();
    }

    @Bean
    public OffersFetchable OffersFetcherFromExternalServer(RestTemplate restTemplate,
                                                           @Value("${joboffers.offers.http.client.config.uri}") String uri,
                                                           @Value("${joboffers.offers.http.client.config.port}") int port) {
        return new OffersRestTemplate(restTemplate, uri, port);
    }
}
