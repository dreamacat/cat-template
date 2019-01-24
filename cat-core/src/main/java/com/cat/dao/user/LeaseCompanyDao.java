package com.cat.dao.user;

import com.cat.model.LeaseCompanyDO;
import java.util.Map;

public interface LeaseCompanyDao {

	int updateByCode(LeaseCompanyDO companyDO);

	LeaseCompanyDO findLeaseCompanyProperty(Map<String, String> params);

	boolean insertLeaseCompany(LeaseCompanyDO companyDO);
}
