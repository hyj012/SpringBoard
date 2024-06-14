package com.itwillbs.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.itwillbs.domain.BoardVO;
import com.itwillbs.service.BoardService;

@Controller
@RequestMapping(value = "/board/*")
public class BoardController {
	
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	//서비스객체 주입
	@Inject
	private BoardService bService;
	
	
	//게시판 글쓰기 - GET
	//http://localhost:8088/board/regist
	@RequestMapping(value = "/regist", method = RequestMethod.GET)
	public void registGET() throws Exception {
		logger.debug("게시판 글쓰기 GET- 사용자의 정보를 입력받음");
		logger.debug("연결된 view페이지 이동");
	} 
	
	//게시판 글쓰기 - POST
	@RequestMapping(value = "/regist", method = RequestMethod.POST)
	public String registPOST(BoardVO vo, RedirectAttributes rttr) throws Exception {
		logger.debug("게시판 글쓰기 POST - 입력된 데이터를 처리");
		
		//한글인코딩(필터처리)
		//전달정보 저장
		logger.debug("vo"+vo);
		
		//서비스 -> DAO에 동작 호출
		bService.regist(vo);
		
		// 글쓰기 성공정보를 전달
		rttr.addFlashAttribute("msg", "createOK");
		
		//페이지 이동
		return"redirect:/board/listALL";
		//RedirectAttributes 리다이렉트 쓸때만 사용할 수 있음.
//		return"/board/list";
		
	}
	
	
	// *정보조회 동작, 사용자 정보 입력 => GET방식
	// *정보를 처리하는 동작() 
	
	//http://localhost:8088/board/listALL
	@RequestMapping(value = "/listALL", method = RequestMethod.GET)
	public String listALLGET(Model model) throws Exception {
		logger.debug("listALLGET() 실행");
		
		//서비스의 정보를 통해서 -> DB의 정보를 가져오기(controller역할)
		List<BoardVO> boardList = bService.listALL();
		logger.debug("size :"+boardList.size());
		
		//연결된 view페이지로 정보 전달(controller역할)
		model.addAttribute("boardList", boardList);
		
		return"/board/list";
	}
	
	
	
}
