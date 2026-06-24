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

package cn.org.byc.framework.mybatis.handler;

import cn.org.byc.framework.common.constant.BaseConstants;
import cn.org.byc.framework.mybatis.enums.DataStatusEnum;
import cn.org.byc.framework.security.util.SecurityUtils;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import java.time.LocalDateTime;
import org.apache.ibatis.reflection.MetaObject;

/**
 * @author Ken
 */
public class FieldsFillingHandler implements MetaObjectHandler {

  @Override
  public void insertFill(MetaObject metaObject) {
    final boolean hasCreatedId = metaObject.hasSetter("createdId");
    final boolean hasCreatedName = metaObject.hasSetter("createdName");
    final boolean hasCreatedDate = metaObject.hasSetter("createdDate");

    final boolean hasUpdatedId = metaObject.hasSetter("updatedId");
    final boolean hasUpdatedName = metaObject.hasSetter("updatedName");
    final boolean hasUpdatedDate = metaObject.hasSetter("updatedDate");

    final boolean hasDeleted = metaObject.hasSetter("deleted");
    final boolean hasStatus = metaObject.hasSetter("status");
    final boolean hasVersion = metaObject.hasSetter("version");

    String userName = getUserName();
    Long userId = getUserId();

    if (hasCreatedId && userId != null) {
      final Object createdId = getFieldValByName("createdId", metaObject);
      if (createdId == null) {
        this.setFieldValByName("createdId", userId, metaObject);
      }
    }
    if (hasCreatedName && userName != null) {
      final Object createdName = getFieldValByName("createdName", metaObject);
      if (createdName == null) {
        this.setFieldValByName("createdName", userName, metaObject);
      }
    }
    if (hasCreatedDate) {
      final Object createdDate = getFieldValByName("createdDate", metaObject);
      if (createdDate == null) {
        this.setFieldValByName("createdDate", LocalDateTime.now(), metaObject);
      }
    }

    if (hasUpdatedId && userId != null) {
      final Object updatedId = getFieldValByName("updatedId", metaObject);
      if (updatedId == null) {
        this.setFieldValByName("updatedId", userId, metaObject);
      }
    }
    if (hasUpdatedName && userName != null) {
      final Object updatedName = getFieldValByName("updatedName", metaObject);
      if (updatedName == null) {
        this.setFieldValByName("updatedName", userName, metaObject);
      }
    }
    if (hasUpdatedDate) {
      final Object updatedDate = getFieldValByName("updatedDate", metaObject);
      if (updatedDate == null) {
        this.setFieldValByName("updatedDate", LocalDateTime.now(), metaObject);
      }
    }

    if (hasStatus) {
      final Object status = getFieldValByName("status", metaObject);
      if (status == null) {
        strictInsertFill(metaObject, "status", DataStatusEnum.class, DataStatusEnum.DRAFT);
      }
    }

    if (hasDeleted) {
      this.setFieldValByName("deleted", BaseConstants.NOT_DELETED, metaObject);
    }

    if (hasVersion) {
      this.setFieldValByName("version", BaseConstants.INIT_VERSION, metaObject);
    }
  }

  @Override
  public void updateFill(MetaObject metaObject) {
    final boolean hasUpdatedId = metaObject.hasSetter("updatedId");
    final boolean hasUpdatedName = metaObject.hasSetter("updatedName");
    final boolean hasUpdatedDate = metaObject.hasSetter("updatedDate");

    String userName = getUserName();
    Long userId = getUserId();

    if (hasUpdatedId && userId != null) {
      final Object updatedId = getFieldValByName("updatedId", metaObject);
      if (updatedId == null) {
        this.setFieldValByName("updatedId", userId, metaObject);
      }
    }
    if (hasUpdatedName && userName != null) {
      final Object updatedName = getFieldValByName("updatedName", metaObject);
      if (updatedName == null) {
        this.setFieldValByName("updatedName", userName, metaObject);
      }
    }
    if (hasUpdatedDate) {
      this.setFieldValByName("updatedDate", LocalDateTime.now(), metaObject);
    }
  }

  private Long getUserId() {
    try {
      return SecurityUtils.getCurrentUser().getUserId();
    } catch (Exception ignore) {
    }
    return BaseConstants.SYSTEM_USER_ID;
  }

  private String getUserName() {
    try {
      return SecurityUtils.getCurrentUser().getUserName();
    } catch (Exception ignore) {
    }
    return BaseConstants.SYSTEM_USER_Name;
  }
}
