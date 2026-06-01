package com.byc.sync.job.task;

import java.time.Instant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Scheduled task component for periodic data synchronization.
 *
 * <p>Executes sync operations on a configurable cron schedule, logging
 * each execution tick for monitoring and traceability.
 */
@Component
public class SampleTask {

  private static final Logger log = LoggerFactory.getLogger(SampleTask.class);

  /**
   * Executes the scheduled synchronization task on a configurable cron schedule.
   *
   * <p>Default cron expression: {@code 0 0/5 * * * ?}. Override at runtime via
   * property {@code app.job.sync.cron}.
   */
  @Scheduled(cron = "${app.job.sync.cron:0 0/5 * * * ?}")
  public void run() {
    log.info("[sync-job] tick at {}", Instant.now());
  }
}
