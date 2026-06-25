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

package cn.org.byc.framework.event.config;

import cn.org.byc.framework.event.DomainEventPublisher;
import cn.org.byc.framework.event.LocalDomainEventPublisher;
import cn.org.byc.framework.event.RocketMqDomainEventPublisher;
import cn.org.byc.framework.event.config.properties.EventProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;

/**
 * @author Ken
 */
@AutoConfiguration
@EnableConfigurationProperties(EventProperties.class)
public class DomainEventAutoConfiguration {

  @Bean
  @ConditionalOnProperty(prefix = "smart.event", name = "type", havingValue = "local", matchIfMissing = true)
  public DomainEventPublisher domainEventPublisher(ApplicationEventPublisher eventPublisher) {
    return new LocalDomainEventPublisher(eventPublisher);
  }

  @Bean
  @ConditionalOnProperty(prefix = "smart.event", name = "type", havingValue = "rocketmq")
  public DomainEventPublisher domainEventPublisher(RocketMQTemplate rocketMQTemplate, ObjectMapper objectMapper) {
    return new RocketMqDomainEventPublisher(rocketMQTemplate, objectMapper);
  }
}
