package com.pcwk.ehr.login.controller;

import java.sql.SQLException;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.google.gson.Gson;
import com.pcwk.ehr.cmn.MessageVO;
import com.pcwk.ehr.login.service.LoginService;
import com.pcwk.ehr.user.domain.UserVO;

@Controller
@RequestMapping("login")
public class LoginController {
	final Logger LOG = LogManager.getLogger(getClass());
	
	@Autowired
	LoginService loginService;
	
	@Autowired
	SessionLocaleResolver localeResolver;
	
	public LoginController() {}
	
	
	@GetMapping(value="/localeChange.do",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String localeChange(@RequestParam(value="lang",defaultValue = "kor") String lang
			, Model model, HttpServletRequest  request) {
		String jsonString = "";
		LOG.debug("┌───────────────────────────────────────────┐");
		LOG.debug("│ localeChange                              │");
		LOG.debug("│ lang                                      │"+lang);
		LOG.debug("└───────────────────────────────────────────┘");				
		
		//현재 스레드에서 설정된 Locale이 반환
		Locale  locale = LocaleContextHolder.getLocale();
		String message = "";
		MessageVO  messageVO=new MessageVO();
		
		if("en".equals(lang)) {
			locale = Locale.ENGLISH;
			message = "The region has been changed to English.\n"+Locale.ENGLISH;
		}else if("ko".equals(lang)) {
			locale = Locale.KOREA;
			message = "로케일이 한국으로 변경 되었습니다..\n"+Locale.KOREA;
		}
		
		//<mvc:    LocaleChangeInterceptor 
		model.addAttribute("lang", lang);
		//local변경
		localeResolver.setLocale(request, null, locale);//SessionLocaleResolver
		
		messageVO.setMsgId(lang);
		messageVO.setMsgContents(message);
		jsonString = new Gson().toJson(messageVO);
		
		LOG.debug("│ jsonString                                      │"+jsonString);
		
		return jsonString;
	}
	  
	@RequestMapping(value="/loginView.do")
	public String loginView() {
		String view = "login/login";
		LOG.debug("┌───────────────────────────────────────────┐");
		LOG.debug("│ loginView                                 │");
		LOG.debug("└───────────────────────────────────────────┘");				
				
		return view;
	}
	@RequestMapping(value="/doLogin.do", method = RequestMethod.POST
			,produces = "application/json;charset=UTF-8"
			)
	@ResponseBody// HTTP 요청 부분의 body부분이 그대로 브라우저에 전달된다.
	public String doLogin(UserVO user, HttpSession httpSession)throws SQLException{
		String jsonString = "";
		LOG.debug("┌───────────────────────────────────────────┐");
		LOG.debug("│ doLogin                                   │user:"+user);
		LOG.debug("└───────────────────────────────────────────┘");				
		
		MessageVO  message =new MessageVO();
		
		//입력 validation
		//id null check 
		if(null == user.getUserId() || "".equals(user.getUserId())) {
			message.setMsgId("1");
			message.setMsgContents("아이디를 입력 하세요.");
			
			jsonString = new Gson().toJson(message);
			LOG.debug("jsonString:"+jsonString);
			return jsonString;
		}
		
		
		//pass null check
		if(null == user.getPassword()|| "".equals(user.getPassword())) {
			message.setMsgId("2");
			message.setMsgContents("비밀번호를 입력 하세요.");
			
			jsonString = new Gson().toJson(message);
			LOG.debug("jsonString:"+jsonString);
			return jsonString;
		}		
		
		  
	    int check=loginService.loginCheck(user);
	    if(10==check) {//id확인
	    	message.setMsgId("10");
			message.setMsgContents("아이디를 확인 하세요.");
			
	    }else if(20==check) {//비번확인
	    	message.setMsgId("20");
			message.setMsgContents("비번을 확인 하세요.");	    	
		
	    }else if(30==check) {//비번확인
	    	UserVO outVO = loginService.doSelectOne(user);
	    	message.setMsgId("30");
			message.setMsgContents(outVO.getName()+"님 반갑습니다.");	   
			
			
			
			if(null != outVO) {
				httpSession.setAttribute("user", outVO);
			}			
	    }else {
	    	message.setMsgId("99");
			message.setMsgContents("오류가 발생 했습니다.");	   	    	
	    }
	    jsonString = new Gson().toJson(message);
		LOG.debug("jsonString:"+jsonString);
		
		return jsonString;
	}
	
}
