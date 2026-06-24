package cn.org.byc.framework.mybatis.base.plugin;

import ch.qos.logback.core.util.StringUtil;
import cn.org.byc.framework.mybatis.config.properties.LogProperties;
import com.alibaba.druid.DbType;
import com.alibaba.druid.filter.FilterChain;
import com.alibaba.druid.filter.FilterEventAdapter;
import com.alibaba.druid.proxy.jdbc.JdbcParameter;
import com.alibaba.druid.proxy.jdbc.ResultSetProxy;
import com.alibaba.druid.proxy.jdbc.StatementProxy;
import com.alibaba.druid.sql.SQLUtils;
import java.sql.SQLException;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

/**
 * @author Ken
 */
@Slf4j
@RequiredArgsConstructor
public class SqlLogInterceptor extends FilterEventAdapter {
private static final SQLUtils.FormatOption FORMAT_OPTION = new SQLUtils.FormatOption(false, false);
  private final LogProperties logProperties;

  @Override
  public void statement_close(FilterChain chain, StatementProxy statement) throws SQLException {
    super.statement_close(chain, statement);
    if (!logProperties.isEnabled()){
      return;
    }
    // 打印可执行的 sql
		String sql = statement.getBatchSql();
    // sql 为空直接返回
		if (!StringUtils.hasText(sql)) {
			return;
		}

    int parametersSize = statement.getParametersSize();
		List<Object> parameters = new ArrayList<>(parametersSize);
		for (int i = 0; i < parametersSize; ++i) {
			parameters.add(getJdbcParameter(statement.getParameter(i)));
		}
		String dbType = statement.getConnectionProxy().getDirectDataSource().getDbType();
		String formattedSql = SQLUtils.format(sql, DbType.of(dbType), parameters, FORMAT_OPTION);
		printSql(formattedSql, statement);
  }

  @Override
  protected void statementExecuteBatchAfter(StatementProxy statement, int[] result) {
    statement.setLastExecuteTimeNano();
  }

  @Override
  protected void statementExecuteBatchBefore(StatementProxy statement) {
    statement.setLastExecuteStartNano();
  }

  @Override
  protected void statementExecuteAfter(StatementProxy statement, String sql, boolean result) {
    statement.setLastExecuteTimeNano();
  }

  @Override
  protected void statementExecuteBefore(StatementProxy statement, String sql) {
    statement.setLastExecuteStartNano();
  }

  @Override
  protected void statementExecuteQueryAfter(StatementProxy statement, String sql, ResultSetProxy resultSet) {
    statement.setLastExecuteTimeNano();
  }

  @Override
  protected void statementExecuteQueryBefore(StatementProxy statement, String sql) {
    statement.setLastExecuteStartNano();
  }

  @Override
  protected void statementExecuteUpdateAfter(StatementProxy statement, String sql, int updateCount) {
    statement.setLastExecuteTimeNano();
  }

  @Override
  protected void statementExecuteUpdateBefore(StatementProxy statement, String sql) {
    statement.setLastExecuteStartNano();
  }

  private static Object getJdbcParameter(JdbcParameter jdbcParam) {
		if (jdbcParam == null) {
			return null;
		}
		Object value = jdbcParam.getValue();
		if (value instanceof TemporalAccessor) {
			return value.toString();
		}
		return value;
	}


	private static void printSql(String sql, StatementProxy statement) {
    double executeTimeMs = statement.getLastExecuteTimeNano() / 1_000_000.0;
		String sqlLogger =
			"""
      =================== Sql Logger ===================
      {}
			============ Sql Execute Time: {} ===========
			""";
		log.info(sqlLogger, sql.trim(), String.format("%.3f ms", executeTimeMs));
	}
}
