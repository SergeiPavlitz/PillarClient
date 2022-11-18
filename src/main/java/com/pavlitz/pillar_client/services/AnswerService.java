package com.pavlitz.pillar_client.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class AnswerService implements RestService {

    private final Logger log = LoggerFactory.getLogger(AnswerService.class);

    private final String URL = "http://localhost:8070/answer";

    private final RestTemplate restTemplate;

    public AnswerService() {
        this.restTemplate = new RestTemplate();
    }

    @Override
    public boolean postAnswer(String answer, String type) {

        try {
            Date d = Date.valueOf(LocalDate.now());
            Phrase p = new Phrase(d, answer, type);
            p = restTemplate.postForObject(URL + "/", p, Phrase.class);
            return p != null;
        } catch (RestClientException e) {
            e.printStackTrace();
            return false;
        }

        //get all
//            Phrase[] arr = restTemplate.getForObject(URL + "/listData", Phrase[].class);
//            log.info(Arrays.toString(arr));
    }

    @Override
    public Phrase[] getWeeklyByType(String type) {
        Phrase[] list = new Phrase[0];
        try {
            list = restTemplate.getForObject(URL + "/?pillarTypeWeekly=" + type, Phrase[].class);
            return (list == null) ? new Phrase[0] : list;
        } catch (RestClientException e) {
            e.printStackTrace();
            return new Phrase[0];
        }
    }

    @Override
    public boolean checkConnection() {
        try {
            String response = restTemplate.getForObject(URL + "/getConnect", String.class);
            return response != null && response.equals("connected");
        } catch (RestClientException e) {
            e.printStackTrace();
            return false;
        }
    }

}
