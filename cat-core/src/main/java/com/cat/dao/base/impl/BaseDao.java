package com.cat.dao.base.impl;


import com.cat.constant.LogConstant;
import com.cat.dao.base.IDao;
import com.cat.dao.dialect.Dialect;
import com.cat.dao.dialect.impl.MysqlDialect;
import com.cat.dao.dialect.impl.OracleDialect;
import com.cat.dao.model.RecordCount;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据库持久层操作基本类
 * @author wangyuanf
 * 
 */
@Repository("frameBaseDao")
public class BaseDao implements IDao, InitializingBean {
    
	private Logger logger = LoggerFactory.getLogger(BaseDao.class);
	
	private Dialect dialect = null;
    
	@Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    private JdbcTemplate jdbcTemplate;
    
    
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public SqlSessionTemplate getSqlSessionTemplate() {
        return sqlSessionTemplate;
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    @Override
    public Dialect getDialect() {
		return dialect;
	}
    private SqlSession getBatchSqlSession() {
		return sqlSessionTemplate.getSqlSessionFactory().openSession(
				ExecutorType.BATCH, false);
	}
    @Override
    public void afterPropertiesSet() throws Exception {
        
		String type = sqlSessionTemplate.getConfiguration().getVariables()
                .getProperty("dialect");
        
        if(type==null) {
        	throw new Exception(LogConstant.DAO_CONFIG_NOT_FOUND);
        }
        
        try {
        	String upperType = type.toUpperCase();
        	Dialect.Type databaseType = Dialect.Type.valueOf(upperType);
        	
        	if (dialect == null) {
                switch (databaseType) {
                    case ORACLE:
                        dialect = new OracleDialect();
                        break;
                    case MYSQL:
                        dialect = new MysqlDialect();
                        break;
                    default:
                        dialect = new MysqlDialect();
                        break;
                }

            }
        }
        catch(Exception e) {
        	logger.error(LogConstant.BASEDAO_AFTERPROPERTIESSET_ERROR, e);
        	throw new Exception(LogConstant.DAO_CONFIG_ERROR);
        }
        
    }
    @Override
    public int insertBySqlId(String sqlID, Object param) throws Exception {

        checkSqlId(sqlID);
        return sqlSessionTemplate.insert(sqlID, param);
    }
    @Override
    public int updateBySqlId(String sqlID, Object param) throws Exception {

        checkSqlId(sqlID);

        int res = sqlSessionTemplate.update(sqlID, param);
       
        return res;
    }
    @Override
    public int deleteBySqlId(String sqlID, Object param) throws Exception {

        checkSqlId(sqlID);
        int res = sqlSessionTemplate.delete(sqlID, param);

        return res;
    }
    @Override
    public int executeBySql(String sql, Object... params) throws Exception {

        if (sql == null) {
            throw new Exception(LogConstant.DAO_ARGUMENT_NOT_NULLB);
        }
        return jdbcTemplate.update(sql, params);

    }
    @Override
    public <T> T findBySqlId(String sqlID, Object param, Class<T> resultType) throws Exception {

        checkSqlId(sqlID);
        return sqlSessionTemplate.selectOne(sqlID, param);

    }

    private void checkSqlId(String sqlID) throws Exception {
        if (sqlID == null) {
            throw new Exception(LogConstant.DAO_ARGUMENT_NOT_NULLA);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> List<T> findListBySqlId(String sqlID, Object param, Class<T> resultType) throws Exception {

        checkSqlId(sqlID);
        List<Object> list = sqlSessionTemplate.selectList(sqlID, param);

        return (List<T>) list;
    }
    @Override
    @SuppressWarnings("unchecked")
    public <T> List<T> findListBySqlId(String sqlID, Object param, int pageNo, int pageSize, Class<T> resultType) throws Exception {

        checkSqlId(sqlID);
        int offset = (pageNo - 1) * pageSize;
        List<Object> list = sqlSessionTemplate.selectList(sqlID, param,
                new RowBounds(offset, pageSize));

        return (List<T>) list;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public Map<String, Object> findRecordsWithTotalBySqlId(String sqlID,
            Object param, int pageNo, int pageSize) throws Exception {

        checkSqlId(sqlID);

        int offset = (pageNo - 1) * pageSize;
        List<Object> list = sqlSessionTemplate.selectList(sqlID, param,
                new RowBounds(offset, pageSize));

        RecordCount rc = null;
        if (param instanceof Map) {
            rc = (RecordCount) ((Map) param).get(RecordCount.RECORD_COUNT_KEY);
        } else {
            Field[] fields = param.getClass().getDeclaredFields();
            Field countField = null;
            for (Field field : fields) {
                if (field.getType().isAssignableFrom(RecordCount.class)) {
                    countField = field;
                    break;
                }
            }
            if (countField != null) {
                countField.setAccessible(true);
                try {
                    rc = (RecordCount) countField.get(param);
                } catch (IllegalArgumentException e) {
                	logger.error(LogConstant.BASEDAO_FINDRECORDSWITHTOTALBYSQLID_ERRORA, e);
                } catch (IllegalAccessException e) {
                	logger.error(LogConstant.BASEDAO_FINDRECORDSWITHTOTALBYSQLID_ERRORB, e);
                }
            }
        }

        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("total", rc != null ? rc.getTotal() : list.size());
        resMap.put("rows", list);

        return resMap;
    }
    @Override
    public Map<String, Object> findBySql(String sql, Object... params) throws Exception {

        if (sql == null) {
            throw new Exception(LogConstant.DAO_ARGUMENT_NOT_NULLB);
        }

        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, params);
        Map<String, Object> map = null;
        if (list.size() > 0) {
            map = list.get(0);
        }
        return map;
    }
    @Override
    public List<Map<String, Object>> findListBySql(String sql, Object... params) throws Exception {

        if (sql == null) {
            throw new Exception(LogConstant.DAO_ARGUMENT_NOT_NULLB);
        }
        return jdbcTemplate.queryForList(sql, params);
    }
    @Override
    public List<Map<String, Object>> findListBySql(String sql, int pageNo,
            int pageSize, Object... params) throws Exception {

        if (sql == null) {
            throw new Exception(LogConstant.DAO_ARGUMENT_NOT_NULLB);
        }
        int offset = (pageNo - 1) * pageSize;
        String newSql = dialect.getLimitString(sql, offset, pageSize);
        Object[] newParams = setParameters(params, pageNo, pageSize);

        return jdbcTemplate.queryForList(newSql, newParams);
    }
    @Override
    public Map<String, Object> findRecordsWithTotalBySql(String sql,
            int pageNo, int pageSize, Object... params) throws Exception {

        if (sql == null) {
            throw new Exception(LogConstant.DAO_ARGUMENT_NOT_NULLB);
        }
        String countSql = dialect.getCountString(sql);
        Map<String, Object> map = jdbcTemplate.queryForMap(countSql, params);
        Object num = map.get("n$$");

        int offset = (pageNo - 1) * pageSize;
        String limitSql = dialect.getLimitString(sql, offset, pageSize);
        Object[] newParams = setParameters(params, pageNo, pageSize);

        List<Map<String, Object>> list = jdbcTemplate.queryForList(limitSql,
                newParams);

        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("total", num);
        resMap.put("rows", list);

        return resMap;
    }

    private Object[] setParameters(Object[] params, int pageNo, int pageSize) {

        if (params == null) {
            params = new Object[0];
        }
        int length = params.length;
        Object[] newParams = Arrays.copyOf(params, length + 2);

        if (dialect instanceof OracleDialect) {
            int offset = (pageNo - 1) * pageSize;
            newParams[length] = offset + pageSize;
            newParams[length + 1] = offset;
        } else if (dialect instanceof MysqlDialect) {
            int offset = (pageNo - 1) * pageSize;
            newParams[length] = offset;
            newParams[length + 1] = pageSize;
        }

        return newParams;
    }
    @Override
    public <T> T findByProc(String sql, CallableStatementCallback<T> callback) throws Exception {
        if (sql == null) {
            throw new Exception(LogConstant.DAO_ARGUMENT_NOT_NULLB);
        }

        if (callback == null) {
            throw new Exception(LogConstant.DAO_ARGUMENT_NOT_NULLC);
        }
        return jdbcTemplate.execute(sql, callback);

    }
    @Override
    public <T> T executeByProc(String sql, CallableStatementCallback<T> callback) throws Exception {
    	if (sql == null) {
            throw new Exception(LogConstant.DAO_ARGUMENT_NOT_NULLB);
        }
        if (callback == null) {
            throw new Exception(LogConstant.DAO_ARGUMENT_NOT_NULLC);
        }
        return jdbcTemplate.execute(sql, callback);

    }
    @Override
    public void batchInsert(List list, String sql) throws Exception {
		SqlSession sqlSession = getBatchSqlSession();
		try {
			int batchCount = 200; //Integer.parseInt((String)FrameCacheManager.getValueFromCache(FrameConstant.CacheNames.ConfigCache.name(), FrameConstant.BATCHCOUNT));// 每批commit的个数
			for (int index = 0; index < list.size();) {
				if (batchCount > list.size()) {
					batchCount = list.size();
					sqlSession.insert(sql, list.subList(index, batchCount));
					sqlSession.commit();
					//清理缓存，防止溢出
					sqlSession.clearCache();
					break;// 数据插入完毕,退出循环
				} else {
					sqlSession.insert(sql, list.subList(index, batchCount));
					sqlSession.commit();
					//清理缓存，防止溢出
					sqlSession.clearCache();
					index = batchCount;
					batchCount = index + batchCount;
				}
			}
		} catch (Exception e) {
			logger.error("业务发生异常", e);
			sqlSession.rollback();
			throw new Exception(e);
		}finally{
	        sqlSession.close();
	    } 
	}

}
