package com.domain.quartz;

import org.springframework.stereotype.Component;

import java.time.LocalTime;

/**
 * Created by Serg on 24.05.2018.
 */
@Component
public class MyJob {
    public void run() {
        System.out.println(LocalTime.now());
    }
}
