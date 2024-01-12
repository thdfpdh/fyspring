package com.pcwk.ehr.board.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.pcwk.ehr.board.domain.BoardVO;
import com.pcwk.ehr.board.service.BoardService;
import com.pcwk.ehr.cmn.MessageVO;
import com.pcwk.ehr.cmn.PcwkLogger;
import com.pcwk.ehr.cmn.StringUtil;

@Controller
@RequestMapping("board")
public class BoardController implements PcwkLogger{

	@Autowired
	BoardService service;
	
	public BoardController() {}
	
	@GetMapping(value = "/doRetrieve.do")
	public ModelAndView doRetrieve(BoardVO inVO, ModelAndView modelAndView) throws SQLException{
		LOG.debug("┌───────────────────────────────────┐");
		LOG.debug("│ doRetrieve                        │");
		LOG.debug("│ BoardVO                           │"+inVO);
		LOG.debug("└───────────────────────────────────┘");
		//Default처리
		//페이지 사이즈:10
		if(null != inVO && inVO.getPageSize() == 0) {
			inVO.setPageSize(10L);
		}

		//페이지 번호:1
		if(null != inVO && inVO.getPageNo() == 0) {
			inVO.setPageNo(1L);
		}
		
		//검색구분:""
		if(null != inVO && null == inVO.getSearchDiv()) {
			inVO.setSearchDiv(StringUtil.nvl(inVO.getSearchDiv()));
		}
		//검색어:""
		if(null != inVO && null == inVO.getSearchWord()) {
			inVO.setSearchDiv(StringUtil.nvl(inVO.getSearchWord()));
		}
		LOG.debug("│ BoardVO Default처리                          │"+inVO);
		
		//목록조회
		List<BoardVO>  list = service.doRetrieve(inVO);
		
		//뷰
		modelAndView.setViewName("board/board_list");//  /WEB-INF/views/board/board_list.jsp
		//Model
		modelAndView.addObject("list", list);
		//검색데이터
		modelAndView.addObject("vo", inVO);
		
		return modelAndView;
	}
	
	@PostMapping(value = "/doUpdate.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public MessageVO doUpdate(BoardVO inVO) throws SQLException{
		LOG.debug("┌───────────────────────────────────┐");
		LOG.debug("│ doUpdate                          │");
		LOG.debug("│ BoardVO                           │"+inVO);
		LOG.debug("└───────────────────────────────────┘");
		
		int flag = service.doUpdate(inVO);
		
		String message = "";
		if(1==flag) {
			message = "수정 되었습니다.";
		}else {
			message = "수정 실패.";
		}
		
		MessageVO messageVO=new MessageVO(flag+"",message);
		LOG.debug("│ messageVO                           │"+messageVO);
		return messageVO;
	}
	
	@GetMapping(value = "/doSelectOne.do")
	public String doSelectOne(BoardVO inVO, Model model) throws SQLException, EmptyResultDataAccessException{
		String view = "board/board_mng";///WEB-INF/views/+board/board_mng+.jsp ->/WEB-INF/views/board/board_mng.jsp
		LOG.debug("┌───────────────────────────────────┐");
		LOG.debug("│ doSelectOne                       │");
		LOG.debug("│ BoardVO                           │"+inVO);
		LOG.debug("└───────────────────────────────────┘");			
		if(0 == inVO.getSeq() ) {
			LOG.debug("============================");
			LOG.debug("==nullPointerException===");
			LOG.debug("============================");
			
			throw new NullPointerException("순번을 입력 하세요");
		}
		
		BoardVO  outVO = service.doSelectOne(inVO);
		model.addAttribute("vo", outVO);
		return view;
	}
	
	
	//@RequestMapping(value = "/doSave.do",method = RequestMethod.POST)
	@PostMapping(value = "/doSave.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public MessageVO doSave(BoardVO inVO) throws SQLException{
		LOG.debug("┌───────────────────────────────────┐");
		LOG.debug("│ doSave                            │");
		LOG.debug("│ BoardVO                           │"+inVO);
		LOG.debug("└───────────────────────────────────┘");				
		
		int flag = service.doSave(inVO);
		
		String message = "";
		if(1 == flag) {
			message = "등록 되었습니다.";
		}else {
			message = "등록 실패.";
		}
		
		MessageVO  messageVO=new MessageVO(String.valueOf(flag), message);
		LOG.debug("│ messageVO                           │"+messageVO);
		return messageVO;
	}
	

	@GetMapping(value ="/doDelete.do",produces = "application/json;charset=UTF-8" )//@RequestMapping(value = "/doDelete.do",method = RequestMethod.GET)
	@ResponseBody// HTTP 요청 부분의 body부분이 그대로 브라우저에 전달된다.
	public MessageVO doDelete(BoardVO inVO) throws SQLException{
		LOG.debug("┌───────────────────────────────────┐");
		LOG.debug("│ doDelete                          │");
		LOG.debug("│ BoardVO                           │"+inVO);
		LOG.debug("└───────────────────────────────────┘");		
		if(0 == inVO.getSeq() ) {
			LOG.debug("============================");
			LOG.debug("==nullPointerException===");
			LOG.debug("============================");
			MessageVO messageVO=new MessageVO(String.valueOf("3"), "순번을 선택 하세요.");
			return messageVO;
		} 
		
		
		int flag = service.doDelete(inVO);
		
		String   message = "";
		if(1==flag) {//삭제 성공
			message = inVO.getSeq()+"삭제 되었습니다.";	
		}else {
			message = inVO.getSeq()+"삭제 실패!";
		}
		
		MessageVO messageVO=new MessageVO(String.valueOf(flag), message);
		
		LOG.debug("│ messageVO                           │"+messageVO);
		return messageVO;
	}
	
	
	
}












