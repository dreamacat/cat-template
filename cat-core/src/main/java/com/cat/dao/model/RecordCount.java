package com.cat.dao.model;

/**
 * 封装记录总数的POJO类
 * @author wangyuanf
 * <br />
 * <br />
 * 用法：
 * 当需要使用Mybatis进行总记录数统计时，将该类声明为参数POJO类的成员变量即可<br />
 * 或者在参数Map中添加RecordCount.RECORD_COUNT_KEY键，value为null亦可
 */
public class RecordCount {
	
	public static final String RECORD_COUNT_KEY = "RECORD_COUNT_KEY";
	
	private int total;

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
	
}
