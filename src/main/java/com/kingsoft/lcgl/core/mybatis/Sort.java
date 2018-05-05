package com.kingsoft.lcgl.core.mybatis;

import org.apache.commons.lang.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * 排序对象
 *
 * @author Liu Sixian <liusixian@kingsoft.com>
 */
public class Sort implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 排序方向枚举
     */
    public enum Direction {
        ASC, DESC;

        public static Direction fromString(String value) {
            try {
                return Direction.valueOf(value.toUpperCase(Locale.US));
            } catch (Exception e) {
                return ASC;
            }
        }
    }

    private List<String> orderByFragment = new ArrayList<String>();

    /**
     * 使用指定的字段排序
     *
     * @param property
     * @param direction
     * @return
     */
    public Sort with(String property, Direction direction) {
        if (!property.matches("^[a-zA-Z]\\w*$")) {
            throw new IllegalArgumentException("Invalid property name: " + property);
        }
        orderByFragment.add(property + " " + direction.toString());
        return this;
    }

    /**
     * 判断排序是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return orderByFragment.isEmpty();
    }

    /**
     * 取得排序查询子句
     *
     * @return
     */
    public String getOrderByQuery() {
        if (!orderByFragment.isEmpty()) {
            return " ORDER BY " + StringUtils.join(orderByFragment, ", ");
        }
        return StringUtils.EMPTY;
    }

    @Override
    public String toString() {
        return "Sort{" + getOrderByQuery() + '}';
    }

}
