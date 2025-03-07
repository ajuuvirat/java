package com.example.logcheck.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class TestController {

    @GetMapping("/pass")
    public ResponseEntity testPass() {
        System.out.println("process");
        log.info("success the hit method");
        return ResponseEntity.ok("Success");
    }

    @GetMapping("/failed")
    public ResponseEntity testFailure() {
        try {
            System.out.println("process");
            // Simulating some logic that might fail
            throw new RuntimeException("Simulated exception");
        } catch (RuntimeException e) {
            log.error("Failed the hit method: " + e.getMessage(), e);
            return ResponseEntity.badRequest().body("Failure due to exception: " + e.getMessage());
        }
    }
}

