package cn.org.byc.framework.common.util;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.WebUtils;

/**
 * @author Ken
 */
public class ServletUtils extends WebUtils {
  public static HttpServletRequest getRequest() {
    return ((ServletRequestAttributes) getRequestAttributes()).getRequest();
  }

  public static RequestAttributes getRequestAttributes() {
    return RequestContextHolder.getRequestAttributes();
  }
}
