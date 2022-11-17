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
        log.info("---answer was sended---");
        //get all
//            Phrase[] arr = restTemplate.getForObject(URL + "/listData", Phrase[].class);
//            log.info(Arrays.toString(arr));
    }

    @Override
    public List<Phrase> getWeeklyByType(String type){
        Phrase[] list = restTemplate.getForObject(URL + "/?pillarTypeWeekly=" + type, Phrase[].class);
        if (list == null) {
            return new ArrayList<>();
        }
        return Arrays.asList(list);
    }

    @Override
    public boolean checkConnection(){
        try {
            String response = restTemplate.getForObject(URL + "/getConnect", String.class);
            if(response!=null && response.equals("connected")){
                return true;
            }else{
                System.out.println("Connection refused");
                return false;
            }
        } catch (RestClientException e) {
            e.printStackTrace();
            System.out.println("Connection refused");
            return false;
        }
    }

}
