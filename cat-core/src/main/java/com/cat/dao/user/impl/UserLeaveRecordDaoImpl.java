package com.cat.dao.user.impl;

import com.cat.dao.base.impl.MybatisBaseDao;
import com.cat.dao.user.UserLeaveRecordDao;
import com.cat.model.UserLeaveRecordDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * @author wangxiaoqiang
 */
@Repository
@Slf4j
public class UserLeaveRecordDaoImpl extends MybatisBaseDao implements UserLeaveRecordDao {

	@Override
	public Long insertRecord(UserLeaveRecordDO param) {
		param.setCreateTime(new Date());
		insertBySqlId("UserLeaveRecordMapper.insertRecord", param);
		return param.getId();
	}

	@Override
	public int insertApplyTrace(UserLeaveRecordDO param) {
		param.setCreateTime(new Date());
		return insertBySqlId("UserLeaveRecordMapper.insertRecordTrace", param);
	}

	@Override
	public int updateById(UserLeaveRecordDO param) {
		return updateBySqlId("UserLeaveRecordMapper.updateById", param);

	}

	@Override
	public UserLeaveRecordDO findById(Long applyId) {
		return findBySqlId("UserLeaveRecordMapper.findById", applyId, UserLeaveRecordDO.class);
	}

	@Override
	public List<UserLeaveRecordDO> findRecordList(UserLeaveRecordDO params) {
		List<UserLeaveRecordDO> recordList = null;
		if (StringUtils.isEmpty(params.getTaskId())) {
			recordList  = findListBySqlId("UserLeaveRecordMapper.findRecordByUserId", params, UserLeaveRecordDO.class);
		} else {
			recordList  = findListBySqlId("UserLeaveRecordMapper.findRecordByTaskId", params, UserLeaveRecordDO.class);
		}
		return recordList;
	}
}
