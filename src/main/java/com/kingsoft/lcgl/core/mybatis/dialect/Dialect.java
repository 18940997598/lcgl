package com.kingsoft.lcgl.core.mybatis.dialect;


import com.kingsoft.lcgl.core.mybatis.Pagination;
import com.kingsoft.lcgl.core.mybatis.Sort;

/**
 * 分页方言类
 *
 * @author Liu Sixian <liusixian@kingsoft.com>
 */
abstract public class Dialect {

    /**
     * 使用方言装饰
     *
     * @param sql
     * @param pagination
     * @return
     */
    public String decorate(String sql, Pagination pagination) {
        if (pagination.getSort() != null && !pagination.getSort().isEmpty()) {
            sql = getSortString(sql, pagination.getSort());
        }

        sql = getLimitOffset(sql, pagination.getLimit(), pagination.getOffset());

        return sql;
    }

    /**
     * 将sql转换为总记录数SQL
     *
     * @param sql SQL语句
     * @return 总记录数的sql
     */
    public String getCountString(String sql) {
        return "SELECT COUNT(1) FROM (" + sql + ") tmp_count";
    }

    /**
     * 将sql转换为带排序的SQL
     *
     * @param sql SQL语句
     * @return 总记录数的sql
     */
    public String getSortString(String sql, Sort sort) {
        if (sort == null || sort.isEmpty()) {
            return sql;
        }

        return sql + sort.getOrderByQuery();
    }

    /**
     * 生成指定SQL语句的 limit offset
     *
     * @param sql
     * @param limit
     * @param offset
     * @return
     */
    public abstract String getLimitOffset(String sql, int limit, int offset);
}
