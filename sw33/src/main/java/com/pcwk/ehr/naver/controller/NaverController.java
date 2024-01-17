package com.pcwk.ehr.naver.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pcwk.ehr.cmn.DTO;
import com.pcwk.ehr.cmn.PcwkLogger;
import com.pcwk.ehr.cmn.StringUtil;
import com.pcwk.ehr.code.domain.CodeVO;
import com.pcwk.ehr.code.service.CodeService;
import com.pcwk.ehr.naver.service.NaverSearchService;

@Controller
@RequestMapping("naver")
public class NaverController implements PcwkLogger {

	@Autowired
	NaverSearchService naverSearchService;
	
	@Autowired
	CodeService  codeService;
	
	public NaverController() {}
	
	//naver/naver_api
	
	@GetMapping(value = "/doRetrieve.do"
			,produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doRetrieve(DTO inVO)throws IOException{
		LOG.debug("┌───────────────────────────────────┐");
		LOG.debug("│ doRetrieve                        │");
		LOG.debug("│ inVO                              │"+inVO);
		LOG.debug("└───────────────────────────────────┘");
		
		inVO.setSearchDiv(StringUtil.nvl(inVO.getSearchDiv(),"10"));
		if(null !=inVO && null == inVO.getSearchWord()) {
			inVO.setSearchWord("겨울");
		}
		     
		
		if(null !=inVO && 0 == inVO.getPageNo()) {
			inVO.setPageNo(1);
		}
		
		if(null !=inVO && 0 == inVO.getPageSize()) {
			inVO.setPageSize(10);
		}
		LOG.debug("2│ inVO                              │"+inVO);
		
		String jsonString = naverSearchService.doNaverSearch(inVO);
		
		LOG.debug("jsonString                              │"+jsonString);
		
		return jsonString;
	}
	
	@GetMapping(value="/naverApiView.do")
	public String naverApiView(Model model) throws SQLException {
		LOG.debug("┌───────────────────────────────────┐");
		LOG.debug("│ naverApiView                      │");
		LOG.debug("└───────────────────────────────────┘");
		
		//NAVER_SEARCH,PAGE_SIZE
		Map<String, Object> codes =new HashMap<String, Object>();
		String[] codeStr = {"NAVER_SEARCH","PAGE_SIZE"};
		
		codes.put("code", codeStr);
		List<CodeVO> codeList = this.codeService.doRetrieve(codes);
		
		List<CodeVO> naverSearchList=new ArrayList<CodeVO>();
		List<CodeVO> pageSizeList=new ArrayList<CodeVO>();		
		
		for(CodeVO vo :codeList) {
			if(vo.getMstCode().equals("NAVER_SEARCH")) {
				naverSearchList.add(vo);
			}
			
			if(vo.getMstCode().equals("PAGE_SIZE")) {
				pageSizeList.add(vo);
			}	
			//LOG.debug(vo);
		}
		
		//검색조건
		model.addAttribute("naverSearch", naverSearchList);
		
		model.addAttribute("pageSize", pageSizeList);
		
		return "naver/naver_api";
	}
	
	
	
}
