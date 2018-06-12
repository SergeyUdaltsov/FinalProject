package com.domain.quartz;

import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
public class MyJob {
    public void run() {
        System.out.println(LocalTime.now());
    }
}
