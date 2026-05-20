package com.byc.sync.job.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class SampleTask {
    
    private static final Logger log = LoggerFactory.getLogger(SampleTask.class);
    
    /**
     * Default cron: 0 0/5 * * * ?. Override at runtime via property {@code app.job.sync.cron}.
     */
    @Scheduled(cron = "${app.job.sync.cron:0 0/5 * * * ?}")
    public void run() {
        log.info("[sync-job] tick at {}", Instant.now());
    }
}
