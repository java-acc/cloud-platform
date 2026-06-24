package cn.org.byc.framework.mybatis.handler;

import cn.org.byc.framework.mybatis.config.properties.TenantProperties;
import cn.org.byc.framework.security.util.SecurityUtils;
import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import java.util.List;
import lombok.AllArgsConstructor;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.StringValue;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

/**
 * tenant line handler.
 *
 * @author Ken
 */
@AllArgsConstructor
public class TenantLineHandlerImpl implements TenantLineHandler {

  private final TenantProperties tenantProperties;

  @Override
  public Expression getTenantId() {
    if (StringUtils.hasText(SecurityUtils.getTenantId())) {
      return new StringValue(SecurityUtils.getTenantId());
    }
    return new StringValue(tenantProperties.getDefaultTenantId());
  }

  @Override
  public boolean ignoreTable(String tableName) {
    if (!StringUtils.hasText(tableName)) {
      return true;
    }

    List<String> ignoreTables = tenantProperties.getIgnoreTable();
    if (!CollectionUtils.isEmpty(ignoreTables) && ignoreTables.contains(tableName)) {
      return true;
    }

    // 前缀匹配
    String prefix = tenantProperties.getIgnoreTablePrefix();
    return StringUtils.hasText(prefix) && tableName.startsWith(prefix);
  }

}
