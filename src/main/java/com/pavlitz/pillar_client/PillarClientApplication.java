package com.pavlitz.pillar_client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;

@SpringBootApplication
public class PillarClientApplication {

    private final Logger log = LoggerFactory.getLogger(PillarClientApplication.class);

    private RestTemplate restTemplate = new RestTemplate();

    private final String URL = "http://localhost:8070/answer";

    public static void main(String[] args) {
        SpringApplication.run(PillarClientApplication.class, args);
    }

    @Bean
    public CommandLineRunner run() throws Exception {
        return args -> {
            //Creation
//            Date d = Date.valueOf(LocalDate.now());
//            Phrase p = new Phrase(d, "some answer", "some type");
//            restTemplate.postForObject(URL+"/", p, Phrase.class);
//            log.info("maybe sent");

            //get all
//            Phrase[] arr = restTemplate.getForObject(URL + "/listData", Phrase[].class);
//            log.info(Arrays.toString(arr));


        };
    }

}
