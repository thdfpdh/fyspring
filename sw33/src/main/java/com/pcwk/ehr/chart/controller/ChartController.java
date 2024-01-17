package com.pcwk.ehr.chart.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonArray;
import com.pcwk.ehr.chart.domain.LevelPerMemberVO;
import com.pcwk.ehr.chart.domain.PizzaVO;
import com.pcwk.ehr.cmn.PcwkLogger;
import com.pcwk.ehr.cmn.StringUtil;
import com.pcwk.ehr.user.service.UserService;

@Controller
@RequestMapping("chart")
public class ChartController implements PcwkLogger{

	
	@Autowired
	UserService userService;
	
	public ChartController() {}
	
	@GetMapping("/viewPie.do")
	public String viewPie() {
		LOG.debug("┌───────────────────────────────────┐");
		LOG.debug("│ viewPie                           │");
		LOG.debug("└───────────────────────────────────┘");
		
		
		return "chart/pie_chart";
	}	
	
	@GetMapping(value="/viewPieChart.do",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String viewPieChart(LevelPerMemberVO inVO) throws SQLException {
	//public String viewPieChart(HttpServletRequest req) {
	//public String viewPieChart(@RequestParam(name = "year", defaultValue = "2024") String year) {
		
		//String year = StringUtil.nvl(req.getParameter("year"),"2024");
		LOG.debug("┌───────────────────────────────────┐");
		LOG.debug("│ viewPieChart                      │");
		LOG.debug("│ inVO                              │"+inVO);
		LOG.debug("└───────────────────────────────────┘");
		
		//NULL처리
		inVO.setYear(StringUtil.nvl(inVO.getYear(),"2024"));
		
		List<LevelPerMemberVO> listLevel =userService.levelPerMemberCount(inVO);
		for(LevelPerMemberVO vo :listLevel) {
			LOG.debug(vo);
		}

		
		JsonArray  mainArray=new JsonArray();
		for(LevelPerMemberVO vo  :listLevel) {
			JsonArray  sArray=new JsonArray();
			sArray.add(vo.getLevelName());
			sArray.add(vo.getCount());
			
			mainArray.add(sArray);
		}  
		
		String jsonString = mainArray.toString();
		//[["실버",9],["골드",6],["일반",12]]
		LOG.debug("┌───────────────────────────────────┐");
		LOG.debug("│ jsonString                        │"+jsonString);
		LOG.debug("└───────────────────────────────────┘");		
		
		return jsonString;    
	}
	
	@GetMapping("/viewLineChart.do")
	public String viewLineChart() {
		LOG.debug("┌───────────────────────────────────┐");
		LOG.debug("│ viewLineChart                     │");
		LOG.debug("└───────────────────────────────────┘");
		
		
		return "chart/line_chart";
	}	
}
