package com.pcwk.ehr.naver;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pcwk.ehr.cmn.DTO;
import com.pcwk.ehr.cmn.PcwkLogger;
import com.pcwk.ehr.naver.service.NaverSearchService;

@RunWith(SpringJUnit4ClassRunner.class) //스프링 테스트 컨텍스트 프레임웤그의 JUnit의 확장기능 지정
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"
		,"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class NaverSearchJunitTest implements PcwkLogger {

	
	@Autowired
	NaverSearchService naverSearchService;
	
	@Autowired  //테스트 오브젝트가 만들어지고 나면 스프링 테스트 컨텍스트에 자동으로 객체값으로 주입
	ApplicationContext context;		
	
	DTO  dto;
	
	@Before
	public void setUp() throws Exception {
		LOG.debug("┌───────────────────────────────────┐");
		LOG.debug("│ setUp                             │");
		LOG.debug("└───────────────────────────────────┘");				
		dto=new DTO();
		dto.setPageNo(1);
		dto.setPageSize(10);
		dto.setSearchDiv("20");
		dto.setSearchWord("대만선거");
	}

	
	@Test
	public void doNaverSearch() {

		
		try {
			String jsonString = naverSearchService.doNaverSearch(dto);
			
			LOG.debug("jsonString:"+jsonString);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	@Test
	public void beans() {
		LOG.debug("┌───────────────────────────────────┐");
		LOG.debug("│ beans                             │");
		LOG.debug("│ context                           │"+context);
		LOG.debug("│ naverSearchService                │"+naverSearchService);
		LOG.debug("└───────────────────────────────────┘");	
		
		
		assertNotNull(naverSearchService);
		assertNotNull(context);
		
	}

}
