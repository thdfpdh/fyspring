package com.pcwk.ehr.user.dao;

import java.sql.SQLException;
import java.util.List;

import com.pcwk.ehr.cmn.WorkDiv;
import com.pcwk.ehr.user.domain.UserVO;

public interface UserDao extends WorkDiv<UserVO>{
	
	//유저 목록 조회
	List<UserVO> getAll(UserVO inVO) throws SQLException;
	
	//등록된 유저 숫자
	int geCount(UserVO inVO) throws SQLException;

}
