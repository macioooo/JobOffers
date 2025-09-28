package org.joboffers.infrastructure.offers.http;

import lombok.AllArgsConstructor;
import org.joboffers.domain.offers.OffersFetchable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
@AllArgsConstructor
public class OffersClientConfig {

    private final OffersRestTemplateProperties properties;
    @Bean
    public RestTemplateErrorsResponseHandler offersErrorsResponseHandler() {
        return new RestTemplateErrorsResponseHandler();
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateErrorsResponseHandler restTemplateErrorsResponseHandler) {
        return new RestTemplateBuilder()
                .errorHandler(restTemplateErrorsResponseHandler)
                .setReadTimeout(Duration.ofMillis(properties.readTimeout()))
                .setConnectTimeout(Duration.ofMillis(properties.connectionTimeout()))
                .build();
    }

    @Bean
    public OffersFetchable OffersFetcherFromExternalServer(RestTemplate restTemplate) {
        return new OffersRestTemplate(restTemplate, properties.uri(), properties.port());
    }
}
