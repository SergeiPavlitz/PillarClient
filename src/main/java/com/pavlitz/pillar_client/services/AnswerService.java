package com.pavlitz.pillar_client.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.Date;
import java.time.LocalDate;

@Service
public class AnswerService implements RestService{

    private final Logger log = LoggerFactory.getLogger(AnswerService.class);

    private final String URL = "http://localhost:8070/answer";

    private final RestTemplate restTemplate;

    public AnswerService() {
        this.restTemplate = new RestTemplate();
    }

    @Override
    public void postAnswer(String answer, String type) {

        Date d = Date.valueOf(LocalDate.now());
        Phrase p = new Phrase(d, answer, type);
        restTemplate.postForObject(URL + "/", p, Phrase.class);
        log.info("maybe sent");
        System.out.println("---answer was sended---");
        //get all
//            Phrase[] arr = restTemplate.getForObject(URL + "/listData", Phrase[].class);
//            log.info(Arrays.toString(arr));
    }

}
