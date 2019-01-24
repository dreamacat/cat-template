package com.cat.dao.user;

import com.cat.model.UserLeaveRecordDO;

import java.util.List;

public interface UserLeaveRecordDao {

	int updateById(UserLeaveRecordDO recordDO);

	UserLeaveRecordDO findById(Long applyId);

	List<UserLeaveRecordDO> findRecordList(UserLeaveRecordDO recordDO);


	Long insertRecord(UserLeaveRecordDO recordDO);

	int insertApplyTrace(UserLeaveRecordDO recordDO);
}
