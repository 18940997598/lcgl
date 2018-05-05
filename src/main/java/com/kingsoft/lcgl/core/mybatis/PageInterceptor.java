package com.kingsoft.lcgl.core.mybatis;

import com.kingsoft.lcgl.core.mybatis.dialect.Dialect;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.MappedStatement.Builder;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * MyBatis物理分页插件
 *
 * @author Liu Sixian <liusixian@kingsoft.com>
 */
@Intercepts({
        @Signature(
                type = Executor.class,
                method = "query",
                args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})})
public class PageInterceptor implements Interceptor {

    private static Logger LOG = LoggerFactory.getLogger(PageInterceptor.class);
    private static int MAPPED_STATEMENT_INDEX = 0;
    private static int PARAMETER_INDEX = 1;
    private static int ROWBOUNDS_INDEX = 2;
    /**
     * 数据库方言对象
     */
    private Dialect dialect;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        Executor executor = (Executor) invocation.getTarget();
        Object[] queryArgs = invocation.getArgs();

        // 只处理分页的
        if (!(queryArgs[ROWBOUNDS_INDEX] instanceof Pagination)) {
            return invocation.proceed();
        }

        MappedStatement ms = (MappedStatement) queryArgs[MAPPED_STATEMENT_INDEX];
        Object parameter = queryArgs[PARAMETER_INDEX];
        Pagination pagination = (Pagination) queryArgs[ROWBOUNDS_INDEX];

        // 查询条数, 并构造新的查询语句
        queryArgs[MAPPED_STATEMENT_INDEX] = buildPagingMappedStatement(ms, parameter, pagination);
        queryArgs[ROWBOUNDS_INDEX] = new RowBounds(RowBounds.NO_ROW_OFFSET, RowBounds.NO_ROW_LIMIT);

        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties prprts) {
        String dialectClass = prprts.getProperty("dialectClass");
        try {
            dialect = (Dialect) Class.forName(dialectClass).newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException("cannot create dialect instance by dialectClass:" + dialectClass, e);
        }
    }

    /**
     * 构造新的分页MappedStatement
     */
    private MappedStatement buildPagingMappedStatement(MappedStatement ms, Object parameter, Pagination pagination) {
        final BoundSql boundSql = ms.getBoundSql(parameter);
        String sql = boundSql.getSql().trim();
        if (sql.endsWith(";")) {
            sql = sql.substring(0, sql.length() - 1);
        }
        // 查询总记录数
        Long count = queryTotalRecords(ms, parameter, boundSql, sql);
        pagination.setTotalRecord(count);

        // 构造分页查询SQL
        sql = dialect.decorate(sql, pagination);
        final BoundSql newBoundSql = copyFromBoundSql(ms, boundSql, sql);
        return copyFromMappedStatement(ms, new SqlSource() {
            @Override
            public BoundSql getBoundSql(Object o) {
                return newBoundSql;
            }
        });
    }

    /**
     * 统计总记录数
     */
    private Long queryTotalRecords(MappedStatement ms, Object parameter, BoundSql boundSql, String sql) {
        String sqlCount = dialect.getCountString(sql);
        LOG.debug("Total count SQL: {}", sqlCount);
        LOG.debug("Total count Parameters: {}", parameter);
        try (
                Connection connection = ms.getConfiguration().getEnvironment().getDataSource().getConnection();
                PreparedStatement pstmt = connection.prepareStatement(sqlCount);) {
            BoundSql countBoundSql = new BoundSql(ms.getConfiguration(),
                    sqlCount, boundSql.getParameterMappings(), parameter);
            DefaultParameterHandler handler = new DefaultParameterHandler(ms, parameter, countBoundSql);
            handler.setParameters(pstmt);
            try (ResultSet rs = pstmt.executeQuery()) {
                long count = 0;
                if (rs.next()) {
                    count = rs.getLong(1);
                }
                LOG.debug("Total count: {}", count);
                return count;
            }
        } catch (SQLException ex) {
            LOG.error("Total count failed: ", ex);
        }
        return 0L;
    }

    /**
     * 复制为新的BoundSql
     *
     * @param ms
     * @param boundSql
     * @param sql
     * @return
     */
    private BoundSql copyFromBoundSql(MappedStatement ms, BoundSql boundSql, String sql) {
        BoundSql newBoundSql = new BoundSql(ms.getConfiguration(), sql, boundSql.getParameterMappings(), boundSql.getParameterObject());
        for (ParameterMapping mapping : boundSql.getParameterMappings()) {
            String prop = mapping.getProperty();
            if (boundSql.hasAdditionalParameter(prop)) {
                newBoundSql.setAdditionalParameter(prop, boundSql.getAdditionalParameter(prop));
            }
        }
        return newBoundSql;
    }

    /**
     * 复制为新的MappedStatement
     *
     * @param ms
     * @param newSqlSource
     * @return
     */
    private MappedStatement copyFromMappedStatement(MappedStatement ms, SqlSource newSqlSource) {
        Builder builder = new Builder(ms.getConfiguration(), ms.getId(), newSqlSource, ms.getSqlCommandType());

        builder.resource(ms.getResource());
        builder.fetchSize(ms.getFetchSize());
        builder.statementType(ms.getStatementType());
        builder.keyGenerator(ms.getKeyGenerator());
        if (ms.getKeyProperties() != null && ms.getKeyProperties().length > 0) {
            builder.keyProperty(StringUtils.join(ms.getKeyProperties(), ","));
        }

        //setStatementTimeout()
        builder.timeout(ms.getTimeout());

        //setStatementResultMap()
        builder.parameterMap(ms.getParameterMap());

        //setStatementResultMap()
        builder.resultMaps(ms.getResultMaps());
        builder.resultSetType(ms.getResultSetType());

        //setStatementCache()
        builder.cache(ms.getCache());
        builder.flushCacheRequired(ms.isFlushCacheRequired());
        builder.useCache(ms.isUseCache());

        return builder.build();
    }
}
