package com.pavlitz.pillar_client.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.sql.Date;
import java.time.LocalDate;

@Service
public class AnswerService implements RestService {

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

    }

    @Override
    public Phrase[] getWeeklyByType(String type) {
        Phrase[] list;
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
