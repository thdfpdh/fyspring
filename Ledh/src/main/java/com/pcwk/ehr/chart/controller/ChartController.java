package com.pcwk.ehr.chart.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonArray;
import com.pcwk.ehr.chart.domain.PizzaVO;
import com.pcwk.ehr.cmn.PcwkLogger;

@Controller
@RequestMapping("chart")
public class ChartController implements PcwkLogger{

	
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
	public String viewPieChart() {
		LOG.debug("┌───────────────────────────────────┐");
		LOG.debug("│ viewPieChart                      │");
		LOG.debug("└───────────────────────────────────┘");
		PizzaVO  pizza01 = new PizzaVO("Mushrooms",3);
		PizzaVO  pizza02 = new PizzaVO("Onions",1);
		PizzaVO  pizza03 = new PizzaVO("Olives",1);
		PizzaVO  pizza04 = new PizzaVO("Zucchini",1);
		PizzaVO  pizza05 = new PizzaVO("Pepperoni",2);
		
		List<PizzaVO> list=new ArrayList<PizzaVO>();
		list.add(pizza01);
		list.add(pizza02);
		list.add(pizza03);
		list.add(pizza04);
		list.add(pizza05);
		/*
		[
			[],
			[],
			[],
			[],
			[],
		]
		*/
		
		JsonArray  mainArray=new JsonArray();
		for(PizzaVO vo  :list) {
			JsonArray  sArray=new JsonArray();
			sArray.add(vo.getTopping());
			sArray.add(vo.getSlices());
			
			mainArray.add(sArray);
		}  
		
		String jsonString = mainArray.toString();
//		[["Mushrooms",3],
//		   ["Onions",1],
//		   ["Olives",1],
//		   ["Zucchini",1],
//		   ["Pepperoni",2]]
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
