package com.booking.hotel.communicator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Map;

@Service
public class RatingServiceCommunicator {

    private final RestTemplate restTemplate;

    @Autowired
    public RatingServiceCommunicator(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public long getRating(String id){
        String url = "http://localhost:8081/rating/id/";
//        ResponseEntity<Long> responseEntity = this.restTemplate.getForEntity(url+id, Long.class);
//        return responseEntity.getBody();
        Long ratingResponse = this.restTemplate.getForObject(url+id, Long.class);
        return  ratingResponse;
    }

    public void addRating(Map<String, Long> ratingMap) {
        String url = "http://localhost:8081/rating/add";
        //this.restTemplate.postForObject(url, ratingMap, Object.class);
        HttpEntity<Map<String, Long>> requestEntity = new HttpEntity<Map<String, Long>>(ratingMap);
        this.restTemplate.exchange(url, HttpMethod.POST, requestEntity, Object.class);
    }

    public void updateRating(Map<String, Long> updatedRating) {
        String url = "http://localhost:8081/rating/update";
        HttpEntity<Map<String, Long>> ratingEntity = new HttpEntity<Map<String, Long>>(updatedRating);
        this.restTemplate.exchange(url, HttpMethod.PUT, ratingEntity, Object.class);
    }


    public void deleteRating(String id) {
        String url = "http://localhost:8081/rating/id/";
        this.restTemplate.exchange(url+id, HttpMethod.DELETE,null,Object.class);
    }

}
