package cn.org.byc.framework.security.util;

import cn.org.byc.framework.common.util.ConvertUtils;
import cn.org.byc.framework.common.util.ServletUtils;
import cn.org.byc.framework.common.util.SpringUtils;
import cn.org.byc.framework.security.CurrentUser;
import cn.org.byc.framework.security.config.properties.TokenProperties;
import cn.org.byc.framework.security.constant.SecurityKeyWord;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

/**
 * @author Ken
 */
public class SecurityUtils {

  private static final String CURRENT_USER_IN_REQUEST_ATTRIBUTE_NAME = "CURRENT_USER";

  private static String BASE64_SECURITY;

  private static SecurityKeyWord keyWord;
  private static TokenProperties tokenProperties;

  public static CurrentUser getCurrentUser() {
    HttpServletRequest request = ServletUtils.getRequest();
    Assert.notNull(request, "request can't be null");

    Object currentUser = request.getAttribute(CURRENT_USER_IN_REQUEST_ATTRIBUTE_NAME);
    if (currentUser == null) {
      currentUser = getCurrentUser(request);
    }
    return (CurrentUser) currentUser;
  }

  public static CurrentUser getCurrentUser(HttpServletRequest request) {
    return getCurrentUser(getClaims(request));
  }

  public static CurrentUser getCurrentUser(String token) {
    return getCurrentUser(getClaims(token));
  }

  public static Claims getClaims(HttpServletRequest request) {
    String token = request.getHeader(getKeyWord().getAuthTokenInHeaderKey());
    if (!StringUtils.hasText(token)) {
      token = request.getParameter(getKeyWord().getAuthTokenInParamKey());
    }
    return getClaims(token);
  }

  public static CurrentUser getCurrentUser(Claims claims) {
    Assert.notNull(claims, "claims can't be null");

    String clientId = ConvertUtils.toString(claims.get(getKeyWord().getClientIdKey()));
    Long userId = ConvertUtils.toLong(claims.get(getKeyWord().getUserIdKey()));
    String tenantId = ConvertUtils.toString(claims.get(getKeyWord().getTenantIdKey()));
    String roleId = ConvertUtils.toString(claims.get(getKeyWord().getRoleIdKey()));
    String deptId = ConvertUtils.toString(claims.get(getKeyWord().getDeptIdKey()));
    String userCode = ConvertUtils.toString(claims.get(getKeyWord().getUserCodeKey()));
    String roleName = ConvertUtils.toString(claims.get(getKeyWord().getRoleNameKey()));
    String userName = ConvertUtils.toString(claims.get(getKeyWord().getUserNameKey()));
    CurrentUser currentUser = new CurrentUser();
    currentUser.setClientId(clientId);
    currentUser.setUserId(userId);
    currentUser.setTenantId(tenantId);
    currentUser.setUserCode(userCode);
    currentUser.setRoleId(roleId);
    currentUser.setDeptId(deptId);
    currentUser.setRoleName(roleName);
    currentUser.setUserName(userName);
    return currentUser;
  }

  public static Claims getClaims(String token) {
    boolean isBearer = Boolean.FALSE;
    if (token.length() > getKeyWord().getAuthLength()) {
      String headStr = token.substring(0, getKeyWord().getAuthLength() - 1).toLowerCase();
      isBearer = headStr.compareTo(getKeyWord().getBearerKey()) == 0;
    }
    if (isBearer) {
      token = token.substring(getKeyWord().getAuthLength());
    }

    return Jwts.parser()
        .verifyWith(Keys.hmacShaKeyFor(Base64.getDecoder().decode(getBase64Security()))).build()
        .parseSignedClaims(token)
        .getPayload();
  }

  public static String getTenantId() {
    return getCurrentUser().getTenantId();
  }

  private static SecurityKeyWord getKeyWord() {
    if (keyWord == null) {
      keyWord = SpringUtils.getBean(SecurityKeyWord.class);
    }
    return keyWord;
  }

  private static String getBase64Security() {
    if (BASE64_SECURITY == null) {
      BASE64_SECURITY = Base64.getEncoder()
          .encodeToString(getTokenProperties().getSignKey().getBytes(StandardCharsets.UTF_8));
    }
    return BASE64_SECURITY;
  }

  private static TokenProperties getTokenProperties() {
    if (tokenProperties == null) {
      tokenProperties = SpringUtils.getBean(TokenProperties.class);
    }
    return tokenProperties;
  }


}
