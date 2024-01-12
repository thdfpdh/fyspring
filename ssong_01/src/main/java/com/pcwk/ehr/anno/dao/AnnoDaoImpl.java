package com.pcwk.ehr.anno.dao;

import java.sql.SQLException;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pcwk.ehr.anno.domain.AnnoVO;

@Repository
public class AnnoDaoImpl implements AnnoDao{
	
	final String NAMESPACE = "com.pcwk.ehr.anno";
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;//db연결 객체

	@Override
	public int doUpdate(AnnoVO inVO) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int doDelete(AnnoVO inVO) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public AnnoVO doSelectOne(AnnoVO inVO) throws SQLException {
		AnnoVO outVO = new AnnoVO();
		
		String statement = this.NAMESPACE+".doSelectOne";
		outVO = this.sqlSessionTemplate.selectOne(statement,inVO);
		System.out.println("outVO:"+outVO);
		
		return outVO;
	}

	@Override
	public int doSave(AnnoVO inVO) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<AnnoVO> doRetrieve(AnnoVO inVO) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
