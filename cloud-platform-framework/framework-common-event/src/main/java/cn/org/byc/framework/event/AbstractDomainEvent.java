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

import java.time.Instant;
import java.util.UUID;

/**
 * @author Ken
 */
public abstract class AbstractDomainEvent implements DomainEvent {

    private final String eventId;

    private final Long tenantId;

    private final String aggregateId;

    private final Instant occurredAt;

    private final String traceId;

    protected AbstractDomainEvent(
            Long tenantId,
            String aggregateId,
            String traceId
    ) {
        this.eventId = UUID.randomUUID().toString();
        this.tenantId = tenantId;
        this.aggregateId = aggregateId;
        this.traceId = traceId;
        this.occurredAt = Instant.now();
    }

    @Override
    public String eventId() {
        return eventId;
    }

    @Override
    public Long tenantId() {
        return tenantId;
    }

    @Override
    public String aggregateId() {
        return aggregateId;
    }

    @Override
    public Instant occurredAt() {
        return occurredAt;
    }

    @Override
    public String traceId() {
        return traceId;
    }

}
