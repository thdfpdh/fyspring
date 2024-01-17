package com.pcwk.ehr.user.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;

import com.pcwk.ehr.chart.domain.LevelPerMemberVO;
import com.pcwk.ehr.cmn.WorkDiv;
import com.pcwk.ehr.user.domain.UserVO;

public interface UserDao extends WorkDiv<UserVO> {
    
	List<LevelPerMemberVO> levelPerMemberCount(LevelPerMemberVO inVO)  throws SQLException;
	
	List<UserVO> getAll(UserVO inVO) throws SQLException;

	int getCount(UserVO inVO) throws SQLException;
	
	int idDuplicateCheck(UserVO inVO) throws SQLException;

}