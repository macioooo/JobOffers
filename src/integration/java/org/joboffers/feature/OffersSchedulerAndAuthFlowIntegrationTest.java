package org.joboffers.feature;

import com.fasterxml.jackson.core.type.TypeReference;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.joboffers.BaseIntegrationTest;
import org.joboffers.SampleOfJobOffersResponse;
import org.joboffers.domain.offers.dto.OfferResponseDto;
import org.joboffers.infrastructure.offers.scheduler.OffersScheduler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class OffersSchedulerAndAuthFlowIntegrationTest extends BaseIntegrationTest implements SampleOfJobOffersResponse {
    @Autowired
    OffersScheduler scheduler;

    @Test
    public void givenEmptySystem_whenUserRegistersAndSchedulerRuns_thenUserSeesUpdatedOffers() throws Exception {
//        Step 1: external server is empty
        wireMockServer.stubFor(WireMock.get("/offers")
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", "application/json")
                        .withBody(bodyWithNoOffers())));
//        Step 2: scheduler ran first time and made GET request to external server and system added zero offers to database
        List<OfferResponseDto> newOffers = scheduler.fetchAllOffersAndSaveIfNotExists();
        assertThat(newOffers).hasSize(0);


//        Step 3: user tried to get JWT token by POST /token with username = abcd and password = dcba and system returned UNAUTHORIZED(401)
//        Step 4: user tried to get offers by GET /offers but system returned UNAUTHORIZED(401) (user has no token)
//        Step 5: user made request POST /register with username = abcd and password = dcba and system returned OK(200)
//        Step 6: user made request POST /token to get jwt token and server returned OK(200) and jwttoken = AAAA.BBBB.CCCC
//        Step 6: user made request GET /offers with header "Authorization: Bearer AAAA.BBBB.CCCC" and server returned OK(200), with 0 offers
        ResultActions perform = mockMvc.perform(get("/offers"));
        final var actualStatus = perform.andExpect(status().isOk()).andReturn();
        final var emptyResponse = actualStatus.getResponse().getContentAsString();
        final var result = objectMapper.readValue(emptyResponse, new TypeReference<List<OfferResponseDto>>() {
        });
        assertThat(result.isEmpty());
        //then
//        Step 7: external server has two new offers
//        Step 8: scheduler ran second time and made GET request to external rever and system added two new offers to database with ids: 1000, 2000
//        Step 9: user made request GET /offers with header "Authorization: Bearer AAAA.BBBB.CCCC" and server returned OK(200), with 2 offers (1000,2000)
//        Step 10: user made request GET /offers
//        Step 11: user is looking at all offers GET /offers/5000 with header "Authorization: Bearer AAAA.BBBB.CCCC" and server returned NOT_FOUND(404) "offer with id 5000 not found"
//        Step 12: user is looking at all offers GET /offers/1000 with header "Authorization: Bearer AAAA.BBBB.CCCC" and server returned OK(200), with offer id:1000.
//        Step 13: external server has two new offers
//        Step 14: scheduler ran third time and made GET request to external rever and system added two new offers to database with ids: 3000, 4000
//        Step 15: user made request GET /offers with header "Authorization: Bearer AAAA.BBBB.CCCC" and server returned OK(200), with 4 offers (1000,2000,3000,4000)


    }

}
