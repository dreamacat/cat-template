package com.cat.dao.dialect.impl;

import com.cat.dao.dialect.Dialect;

/**
 * Oracle数据库方言
 * @author wangyuanf
 *
 */
public class OracleDialect extends Dialect {

	@Override
	public String getLimitString(String sql, int offset, int limit) {
		StringBuilder str = new StringBuilder();
		str.append("SELECT * FROM (SELECT e$$.*, ROWNUM rn FROM (");
		str.append(sql);
		str.append(") e$$ where ROWNUM <= ?" + ") where rn > ?");
		return str.toString();
	}

}