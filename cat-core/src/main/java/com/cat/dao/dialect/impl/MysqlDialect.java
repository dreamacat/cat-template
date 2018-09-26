package com.cat.dao.dialect.impl;

import com.cat.dao.dialect.Dialect;

/**
 * Mysql数据库方言
 * @author wangyuanf
 *
 */
public class MysqlDialect extends Dialect {

    @Override
    public String getLimitString(String sql, int offset, int limit) {
    	StringBuilder str = new StringBuilder();   
    	str.append(sql).append(" limit ? , ?");   
    	return str.toString();
    }

}