package com.cat.dao.user.impl;

import com.cat.dao.base.impl.MybatisBaseDao;
import com.cat.dao.user.LeaseCompanyDao;
import com.cat.model.LeaseCompanyDO;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

/**
 * @author wangxiaoqiang
 */
@Repository
@Slf4j
public class LeaseCompanyDaoImpl extends MybatisBaseDao implements LeaseCompanyDao {

//	@Override
//	public void addUser(User user) throws Exception {
//		Map<String,String> map = new HashMap<String,String>();
//		insertBySqlId("userMapper.addUser", user);
//	}
//
//	@Override
//	public void updateUser(User user) throws Exception {
//		updateBySqlId("userMapper.updateUser", user);
//	}
//
//	@Override
//	public void deleteUser(Map<String, String> params) throws Exception {
//		deleteBySqlId("userMapper.deleteUser", params);
//	}

	@Override
	public int updateByCode(LeaseCompanyDO companyDO) {
		try {
			int res = updateBySqlId("LeaseCompanyMapper.updateByCode", companyDO);
			return res;
		} catch (Exception e) {
			log.error("error", e);
		}
		return 0;
	}

	@Override
	public LeaseCompanyDO findLeaseCompanyProperty(Map<String, String> params) {
		LeaseCompanyDO leaseCompanyDO = null;
		try {
			leaseCompanyDO  = findBySqlId("LeaseCompanyMapper.findLeaseCompanyProperty", params, LeaseCompanyDO.class);
		} catch (Exception e) {
			log.error("error", e);
		}
		return leaseCompanyDO;
	}

	@Override
	public boolean insertLeaseCompany(LeaseCompanyDO companyDO) {
		int count = 0;
		try {
			count = insertBySqlId("LeaseCompanyMapper.insertLeaseCompany", companyDO);
		} catch (Exception e) {
			log.error("error", e);
		}
		return count > 0;
	}
}
