package org.joboffers.domain.offers;

import org.joboffers.domain.offers.dto.JobOffersResponse;
import org.joboffers.domain.offers.dto.OfferRequestDto;
import org.joboffers.domain.offers.dto.OfferResponseDto;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.joboffers.domain.offers.OfferMessages.OFFER_NOT_FOUND;
import static org.junit.jupiter.api.Assertions.*;


class OffersFacadeTest {
    @Test
    public void should_fetch_5_new_offers_when_there_is_no_offers_in_database() {
        //given
        OffersFacade facade = new OffersFacadeTestConfiguration().createFacadeForTests();
        assertThat(facade.findAllOffers()).isEmpty();
        //when
        List<OfferResponseDto> responseDtos = facade.fetchAllOffersAndSaveIfNotExists();
        //then
        assertThat(responseDtos).hasSize(5);
    }

    @Test
    public void should_save_only_2_out_of_4_offers() {
        //given
        OffersFacade facade = new OffersFacadeTestConfiguration(
                new OffersFetchableTestImpl(
                        List.of(
                                new JobOffersResponse("Junior", "Siii", "1300", "urlone.pl"),
                                new JobOffersResponse("Mid", "CompanyTwo", "1500", "urltwo.pl"),
                                new JobOffersResponse("Senior", "CompanyThree", "1800", "urlthree.pl"),
                                new JobOffersResponse("Junior", "Amazon", "13002", "urlfour.pl")
                        )
                )
        ).createFacadeForTests();
        facade.saveOffer(new OfferRequestDto("Junior", "Siii", "1300", "urlone.pl"));
        facade.saveOffer(new OfferRequestDto("Junior", "Diii", "1300", "urltwo.pl"));
        facade.saveOffer(new OfferRequestDto("Senior", "Nordea", "13", "newurl.pl"));
        facade.saveOffer(new OfferRequestDto("Mid", "Ikea", "1555", "newurltwo.pl"));
        List<OfferResponseDto> responses = facade.fetchAllOffersAndSaveIfNotExists();
        assertThat(List.of(
                responses.get(0).offerUrl(),
                responses.get(1).offerUrl()
        ))
                .containsExactlyInAnyOrder("urlthree.pl", "urlfour.pl");
    }

    @Test
    public void should_return_all_offers_that_are_in_database() {
        //given
        OffersFacade facade = new OffersFacadeTestConfiguration().createFacadeForTests();
        List<OfferResponseDto> fetchedOffers = facade.fetchAllOffersAndSaveIfNotExists();
        //when
        List<OfferResponseDto> savedOffers = facade.findAllOffers();
        //then
        assertTrue(fetchedOffers.containsAll(savedOffers));
    }

    @Test
    public void should_return_offer_by_id() {
        //given
        OffersFacade facade = new OffersFacadeTestConfiguration(
                new OffersFetchableTestImpl(
                        List.of(new JobOffersResponse("test", "testest", "123", "testurl.pl"))
                )).createFacadeForTests();
        List<OfferResponseDto> listOfOffers = facade.fetchAllOffersAndSaveIfNotExists();
        //when
        OfferResponseDto responseDto = facade.findOfferById(listOfOffers.get(0).offerId());
        //then
        assertEquals(responseDto, listOfOffers.get(0));
    }

    @Test
    public void should_throw_offer_not_found_exception() {
        //given
        OffersFacade facade = new OffersFacadeTestConfiguration(new OffersFetchableTestImpl(Collections.emptyList())).createFacadeForTests();
        //when//then
        assertThrows(OfferNotFoundException.class, () -> facade.findOfferById("1"), OFFER_NOT_FOUND.message);
    }

    @Test
    public void should_throw_duplicate_key_exception_when_trying_to_save_the_same_url() {
        //given
        OffersFacade facade = new OffersFacadeTestConfiguration(new OffersFetchableTestImpl(Collections.emptyList())).createFacadeForTests();
        OfferRequestDto requestDto = OfferRequestDto.builder()
                .role("test")
                .salary("111")
                .company("cmp")
                .offerUrl("testurl.pl")
                .build();
        facade.saveOffer(requestDto);
        //when//then
        assertThrows(DuplicateKeyException.class, ()-> facade.saveOffer(requestDto), String.format("Offer with url %s is already in database", requestDto.offerUrl()));
    }
}