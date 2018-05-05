package com.kingsoft.lcgl.core.mybatis.dialect;

/**
 * MySQL方言实现类
 *
 * @author Liu Sixian <liusixian@kingsoft.com>
 */
public class MySQLDialect extends Dialect {

    @Override
    public String getLimitOffset(String sql, int limit, int offset) {
        if (offset > 0) {
            return sql + " LIMIT " + offset + ", " + limit;
        } else {
            return sql + " LIMIT " + limit;
        }
    }

}
