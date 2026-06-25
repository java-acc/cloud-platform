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

package cn.org.byc.framework.event;

import java.io.Serializable;
import java.time.Instant;

/**
 * @author Ken
 */
public interface DomainEvent extends Serializable {

  /**
   * 事件唯一ID
   */
  String eventId();

  /**
   * 事件名称
   */
  default String eventName() {
    return this.getClass().getSimpleName();
  }

  /**
   * 租户ID
   */
  Long tenantId();

  /**
   * 聚合根ID
   */
  String aggregateId();

  /**
   * 聚合根类型
   */
  String aggregateType();

  /**
   * 事件发生时间
   */
  Instant occurredAt();

  /**
   * 事件版本
   */
  default int version() {
    return 1;
  }

  /**
   * 追踪ID
   */
  String traceId();
}
