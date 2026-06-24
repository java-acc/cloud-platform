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

package cn.org.byc.framework.security.constant;

/**
 * @author Ken
 */
public interface SecurityKeyWord {
  String AUTH_TOKEN_IN_HEADER = "X-BYC-AUTH";
  String BEARER = "BEARER";

  String X_CLIENT_ID = "X-BYC-CLIENT-ID";
  String X_TENANT_ID = "X-BYC-TENANT-ID";

  String X_USER_ID = "X-BYC-USER-ID";
  String X_ROLE_ID = "X-BYC-ROLE-ID";
  String X_ROLE_NAME = "X-BYC-ROLE-NAME";
  String X_DEPT_ID = "X-BYC-DEPT-ID";
  String X_USER_CODE = "X-BYC-USER-CODE";
  String X_USER_NAME = "X-BYC-USER-NAME";

  Integer SIGN_KEY_LENGTH = 32;
  Integer AUTH_LENGTH = 7;

  default String getAuthTokenInParamKey() {
    return AUTH_TOKEN_IN_HEADER;
  }

  default String getAuthTokenInHeaderKey() {
    return AUTH_TOKEN_IN_HEADER;
  }

  default String getBearerKey() {
    return BEARER;
  }

  default Integer getAuthLength() {
    return AUTH_LENGTH;
  }

  default String getClientIdKey() {
    return X_CLIENT_ID;
  }

  default String getTenantIdKey() {
    return X_TENANT_ID;
  }

  default String getUserIdKey() {
    return X_USER_ID;
  }

  default String getRoleIdKey() {
    return X_ROLE_ID;
  }

  default String getRoleNameKey() {
    return X_ROLE_NAME;
  }

  default String getDeptIdKey() {
    return X_DEPT_ID;
  }

  default String getUserCodeKey() {
    return X_USER_CODE;
  }

  default String getUserNameKey() {
    return X_USER_NAME;
  }
}
