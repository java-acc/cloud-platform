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

package cn.org.byc.framework.mybatis.enums;

import com.baomidou.mybatisplus.annotation.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Ken
 */
@Getter
@AllArgsConstructor
public enum DataStatusEnum implements IEnum<Integer> {
  DRAFT(0, "草稿"),
  ACTIVE(1, "有效"),
  VOID(-1, "作废");

  private final int code;
  private final String desc;
  @Override
  public Integer getValue() {
    return code;
  }
}
