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

package cn.org.byc.framework.ddd;

import cn.org.byc.framework.event.DomainEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 聚合根
 *
 * @param <ID> 主键类型
 *
 * @author Ken
 */
public abstract class AggregateRoot<ID extends Serializable> extends Entity<ID> {

  private final List<DomainEvent> domainEvents = new ArrayList<>();

  /**
   * 注册领域事件
   */
  protected void registerEvent(DomainEvent event) {
    domainEvents.add(event);
  }

  /**
   * 获取领域事件
   */
  public List<DomainEvent> getDomainEvents() {
    return Collections.unmodifiableList(domainEvents);
  }

  /**
   * 清空领域事件
   */
  public void clearDomainEvents() {
    domainEvents.clear();
  }

}