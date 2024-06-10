package com.itwillbs.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	public String registPOST(BoardVO vo) throws Exception {
		logger.debug("게시판 글쓰기 POST - 입력된 데이터를 처리");
		
		//한글인코딩(필터처리)
		//전달정보 저장
		logger.debug("vo"+vo);
		
		//서비스 -> DAO에 동작 호출
		bService.regist(vo);
		
		//페이지 이동
		return"redirect:/board/list";
		
	}
	
	//게시판에서 글쓰기 버튼을 눌렀을때 넘어가는 페이지로 매핑을 해줘야함!
	//http://localhost:8088/board/list
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void listGET() {
		
	}
	
	
	
}
