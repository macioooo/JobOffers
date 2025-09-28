package org.joboffers.infrastructure.offers.scheduler;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.joboffers.domain.offers.OffersFacade;
import org.joboffers.domain.offers.dto.OfferResponseDto;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@AllArgsConstructor
@Log4j2
@Component
public class OffersScheduler {
    private final OffersFacade facade;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    @Scheduled(fixedDelayString = "${joboffers.offers.offersRunOccurence}")
    public List<OfferResponseDto> fetchAllOffersAndSaveIfNotExists() {
        log.info("Started scheduling at {}", dateFormat.format(new Date()));
        List<OfferResponseDto> response = facade.fetchAllOffersAndSaveIfNotExists();
        log.info("Fetched {} new offers", response.size());
        log.info("Stopped fetching offers");
        return response;
    }
}
