package org.joboffers;


import wiremock.net.minidev.json.JSONArray;
import wiremock.net.minidev.json.JSONObject;

public interface SampleOfJobOffersResponse {
    default String bodyWithTwoOffers() {
        JSONObject firstOffer = new JSONObject();
        firstOffer.put("title", "Junior Java Developer");
        firstOffer.put("company", "BlueSoft Sp. z.o.o.");
        firstOffer.put("salary", "8000-10000");
        firstOffer.put("offerUrl", "https://nofluffjobs.com/pl/job/123");
        JSONObject secondOffer = new JSONObject();
        secondOffer.put("title", "Junior Kotlin Developer");
        secondOffer.put("company", "Sii");
        secondOffer.put("salary", "5000-8000");
        secondOffer.put("offerUrl", "https://nofluffjobs.com/pl/job/1231456");
        JSONArray jsonOfferArray = new JSONArray();
        jsonOfferArray.add(firstOffer);
        jsonOfferArray.add(secondOffer);
        return jsonOfferArray.toJSONString();
    }

    default String bodyWithFourOffers() {
        JSONObject firstOffer = new JSONObject();
        firstOffer.put("title", "Junior C# Developer");
        firstOffer.put("company", "Amazon");
        firstOffer.put("salary", "5000-10000");
        firstOffer.put("offerUrl", "https://nofluffjobs.com/pl/job/8888");
        JSONObject secondOffer = new JSONObject();
        secondOffer.put("title", "Python Developer");
        secondOffer.put("company", "Netflix");
        secondOffer.put("salary", "2310-8500");
        secondOffer.put("offerUrl", "https://nofluffjobs.com/pl/job/9999");
        JSONArray jsonOffersArray = new JSONArray();
        jsonOffersArray.add(firstOffer);
        jsonOffersArray.add(secondOffer);
        return jsonOffersArray.toJSONString();
    }

    default String bodyWithNoOffers() {
        return new JSONArray().toJSONString();
    }
}

