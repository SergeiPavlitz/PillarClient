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





    public static void main(String[] args) {
        SpringApplication.run(PillarClientApplication.class, args);
    }


}
