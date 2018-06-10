package com.domain.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.time.Year;

/**
 * Created by Serg on 24.05.2018.
 */
public class ComplexJob extends QuartzJobBean {
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        System.out.println("Complex quartz" + Year.now());
    }
}
