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

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * DDD仓储
 *
 * @param <T> 聚合根
 * @param <ID> 主键
 *
 * @author Ken
 */
public interface Repository<T extends AggregateRoot<ID>,ID extends Serializable> {

    /**
     * 保存
     */
    T save(T aggregate);

    /**
     * 删除
     */
    void delete(ID id);

    /**
     * 根据ID查询
     */
    Optional<T> findById(ID id);

    /**
     * 是否存在
     */
    boolean exists(ID id);

    /**
     * 批量查询
     */
    List<T> findAll(List<ID> ids);

}