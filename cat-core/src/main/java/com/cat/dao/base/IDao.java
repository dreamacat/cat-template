package com.cat.dao.base;

import com.cat.dao.dialect.Dialect;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.jdbc.core.CallableStatementCallback;

import java.util.List;
import java.util.Map;

/**
 * 数据库持久层操作接口类
 * @author wangyuanf
 * <br />
 * <br />
 *	注意：<br />
 *	在增加新的DML接口方法时，请遵循方法名称以execute、insert、update、delete开头的约定；<br />
 *  在增加新的查询接口方法时，请遵循方法名称以find开头、同时第一个参数为sql或sqlID的约定；<br />
 *  以execute、insert、update、delete开头的方法，在框架事务中会被异步执行；<br />
 *  以find开头的方法，在框架事务中会被同步执行。
 *
 */

public interface IDao {
   public SqlSessionTemplate getSqlSessionTemplate();

   public Dialect getDialect();
   
   /**
    * 通过sql键值执行insert操作
    * @param sqlID Mybatis的sql鍵值，组成格式：命名空间.id
    * @param param 参数
    * @return 影响记录数（不一定准确，取决于底层）
    * @throws Exception 
    */
   public int insertBySqlId(String sqlID, Object param) throws Exception;
   
   /**
    * 通过sql键值执行update操作
    * @param sqlID Mybatis的sql鍵值，组成格式：命名空间.id
    * @param param 参数
    * @return 影响记录数（不一定准确，取决于底层）
    * @throws Exception 
    */
   public int updateBySqlId(String sqlID, Object param) throws Exception;
   
   /**
    * 通过sql键值执行delete操作
    * @param sqlID Mybatis的sql鍵值，组成格式：命名空间.id
    * @param param 参数
    * @return 影响记录数（不一定准确，取决于底层）
    * @throws Exception 
    */
   public int deleteBySqlId(String sqlID, Object param) throws Exception;
   
   
   /**
    * 通过sql键值执行查询单条记录操作
    * @param sqlID Mybatis的sql鍵值，组成格式：命名空间.id 
    * @param param 参数
    * @param resultType 返回值类型
    * @return 单条查询记录（如果存在多条结果，只取第一个）
    * @throws Exception 
    */
   public <T> T findBySqlId(String sqlID, Object param, Class<T> resultType) throws Exception;
   
   /**
    * 通过sql键值执行查询多条记录操作
    * @param sqlID Mybatis的sql鍵值，组成格式：命名空间.id
    * @param param 参数
    * @param resultType 返回值类型
    * @return 多条查询记录
    * @throws Exception 
    */
   public <T> List<T> findListBySqlId(String sqlID, Object param, Class<T> resultType) throws Exception;
   
   /**
    * 通过sql键值执行分页查询操作
    * @param sqlID Mybatis的sql鍵值，组成格式：命名空间.id
    * @param param 参数 （欲获得总记录数，参见 {@link com.cat.framework.kernel.dao.model.RecordCount RecordCount}）
    * @param pageNo 分页序号
    * @param pageSize 分页大小
    * @param resultType 返回值类型
    * @return 分页查询记录
    * @throws Exception 
    */
   public <T> List<T> findListBySqlId(String sqlID, Object param, int pageNo, int pageSize, Class<T> resultType) throws Exception;
   
   /**
    * 通过sql语句执行insert、update或者delete操作
    * @param sql sql语句
    * @param params sql语句中，与参数占位符"?"对应的参数值
    * @return 影响记录数（不一定准确，取决于底层）
    * @throws Exception 
    */
   public int executeBySql(String sql, Object... params) throws Exception;
   
   /**
    * 通过sql语句执行查询单条记录操作
    * @param sql sql语句
    * @param params sql语句中，与参数占位符"?"对应的参数值
    * @return 单条查询记录（如果存在多条结果，只取第一个）
    * @throws Exception 
    */
   public Map<String, Object> findBySql(String sql, Object... params) throws Exception;
   
   /**
    * 通过sql语句执行查询多条记录操作
    * @param sql sql语句
    * @param params sql语句中，与参数占位符"?"对应的参数值
    * @return 多条查询记录
    * @throws Exception 
    */
   public List<Map<String, Object>> findListBySql(String sql, Object... params) throws Exception;
   
   /**
    * 通过sql语句执行分页查询操作
    * @param sql sql语句
    * @param pageNo 分页序号
    * @param pageSize 分页大小
    * @param params sql语句中，与参数占位符"?"对应的参数值
    * @return 分页查询记录
    * @throws Exception 
    */
   public List<Map<String, Object>> findListBySql(String sql, int pageNo, int pageSize, Object... params) throws Exception;
   
   /**
    * 通过数据库存储过程、函数进行查询操作
    * @param sql sql语句
    * @param callback 回调对象
    * @return 查询结果
    * @throws Exception 
    */
   public <T> T findByProc(String sql, CallableStatementCallback<T> callback) throws Exception;
   
   /**
    * 通过数据库存储过程、函数进行DML操作
    * @param sql sql语句
    * @param callback 回调对象
    * @return 执行结果
    * @throws Exception 
    */
   public <T> T executeByProc(String sql, CallableStatementCallback<T> callback) throws Exception;

   /**
    * 通过sql键值执行分页查询操作
    * @param sqlID Mybatis的sql鍵值，组成格式：命名空间.id
    * @param param 参数 （欲获得总记录数，参见 {@link com.cat.framework.kernel.dao.model.RecordCount RecordCount}）
    * @param pageNo 分页序号
    * @param pageSize 分页大小
    * @return 分页查询记录（格式为 {total : .. , rows : []}）
    * @throws Exception 
    */
   public Map<String, Object> findRecordsWithTotalBySqlId(String sqlID, Object param, int pageNo, int pageSize) throws Exception;

   /**
    * 通过sql语句执行分页查询操作
    * @param sql sql语句
    * @param pageNo 分页序号
    * @param pageSize 分页大小
    * @param params sql语句中，与参数占位符"?"对应的参数值
    * @return 分页查询记录（格式为 {total : .. , rows : []}）
    * @throws Exception 
    */
   public Map<String, Object> findRecordsWithTotalBySql(String sql, int pageNo, int pageSize, Object... params) throws Exception;
   /**
    * 通过sql语句执行批量插入操作
    * @param sql sql语句
    * @param list 数据集合
    * @throws Exception 
    */
   public void batchInsert(List list, String sql) throws Exception;
}
