package com.pcwk.ehr.template.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pcwk.ehr.cmn.PcwkLogger;

@Controller
@RequestMapping("template")
public class TemplateController implements PcwkLogger {

	
	public TemplateController() {}
	
	@GetMapping("/viewBlank.do")
	public String viewBlank() {
		LOG.debug("┌───────────────────────────────────┐");
		LOG.debug("│ viewBlank                         │");
		LOG.debug("└───────────────────────────────────┘");
		
		return "template/blank";
	}
}
