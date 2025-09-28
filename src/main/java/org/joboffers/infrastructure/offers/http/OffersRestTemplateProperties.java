package org.joboffers.infrastructure.offers.http;

import lombok.Builder;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "joboffers.offers.http.client.config")
@Builder
public record OffersRestTemplateProperties(int port, String uri, long connectionTimeout, long readTimeout) {
}
