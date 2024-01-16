package com.pcwk.ehr.board;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pcwk.ehr.board.dao.BoardDao;
import com.pcwk.ehr.board.domain.BoardVO;
import com.pcwk.ehr.cmn.PcwkLogger;

@RunWith(SpringJUnit4ClassRunner.class) //스프링 테스트 컨텍스트 프레임웤그의 JUnit의 확장기능 지정
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"
		,"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BoardDaoJunitTest implements PcwkLogger{
	@Autowired
	BoardDao dao;
	
	BoardVO  board01;
	BoardVO  board02;
	BoardVO  board03;
	
	BoardVO  searchVO;
	
	@Autowired  //테스트 오브젝트가 만들어지고 나면 스프링 테스트 컨텍스트에 자동으로 객체값으로 주입
	ApplicationContext context;	

	@Before
	public void setUp() throws Exception {
		LOG.debug("┌───────────────────────────────────┐");
		LOG.debug("│ setUp                             │");
		LOG.debug("└───────────────────────────────────┘");
		
		String email = "thdals5123@naver.com";
		String div ="10";
		String title = "바보당";
		String contents = "바보아니예여";
		int readCnt = 100;
		String regDt = "사용하지않음";
		String regId = "이한나";
		String modDt = "사용하지않음";
		String modId = "고송민";
		
		board01 = new BoardVO(dao.getBoardSeq(),email, div, title, contents, readCnt,regDt,regId, modDt, modId);
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
