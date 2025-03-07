package com.example.logcheck;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class LogcheckApplication {

    public static void main(String[] args) {

        log.info("success");
        SpringApplication.run(LogcheckApplication.class, args);
    }
}
