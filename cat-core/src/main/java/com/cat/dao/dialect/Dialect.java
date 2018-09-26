package com.cat.dao.dialect;


/**
 * 数据库方言
 * @author wangyuanf
 *
 */
public abstract class Dialect {

    public static enum Type {
        MYSQL, ORACLE
    }

    public abstract String getLimitString(String sql, int offset, int limit);
    
    public String getCountString(String sql) {   
    	return "SELECT COUNT(*) n$$ FROM (" + sql + ") aliasForTable";   
    }

}