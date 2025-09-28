package org.joboffers.scheduler;

import org.joboffers.BaseIntegrationTest;
import org.joboffers.JobOffersSpringBootApplication;
import org.joboffers.domain.offers.OffersFetchable;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.time.Duration;

import static org.awaitility.Awaitility.await;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest(classes = JobOffersSpringBootApplication.class, properties = "scheduling.enabled = true")
public class OffersSchedulerTest extends BaseIntegrationTest {
    @SpyBean
    OffersFetchable remoteOffers;

    @Test
    public void should_run_http_client_offers_fetching_exactly_given_times() {
        await().atMost(Duration.ofSeconds(2))
                .untilAsserted(() -> verify(remoteOffers, times(1)).fetchOffers());
    }
}
