/*
 * Copyright 2026 Ken
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.org.byc.framework.common.event.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;

/**
 * Application Ready
 *
 * @author Ken
 */
@Slf4j
@AutoConfiguration
public class ApplicationReadyEventListener {

  @EventListener(ApplicationReadyEvent.class)
  public void onApplicationReady(ApplicationReadyEvent event) {
    Environment env = event.getApplicationContext().getEnvironment();

    String applicationName = env.getProperty("spring.application.name", "application");
    // get port
    String port = env.getProperty("local.server.port");

    log.info("[{}] Startup successful, port: {}, profile: {}", applicationName, port, env.getActiveProfiles());
  }
}
