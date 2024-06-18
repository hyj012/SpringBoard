package com.itwillbs.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	
	//게시판 본문보기 - readGET 
	@GetMapping(value = "/read")
	public void readGET(@ModelAttribute("bno") int bno, Model model) throws Exception {
	//@ModelAttribute("bno") int bno
	//=> 주소줄에 있는 데이터를 가져와서 사용하고, 바로 연결된 뷰페이지로 이동${bno}
	//=> 객체를 저장 -> 1:N 관계(N - been(객체), collection)
		
	//@RequestParam)("bno") int bno
	//=> request.getParameter("bno") 동일함, 자동 형변환이 포함(문자,숫자,날짜)
	//=>1:1 관계에서 사용	
		logger.debug("readGET() 실행");
		// 전달정보 저장
		logger.debug("bno :"+bno);
		
		//글 조회(읽음) 카운트 증가 => 조회수 1증가
		bService.updateReadCnt(bno);
		
		//서비스 - DAO 저장된 정보 가져오기
		BoardVO resultVO = bService.getBoard(bno);
		logger.debug("resultVO : {} "+ resultVO);
		
		//전달할 정보를 뷰페이지에 저장(model객체)
		//연결된 뷰페이지이동
		model.addAttribute("resultVO", resultVO);
		 
		
	}
	
	//http://localhost:8088/board/modify
	//게시판 글 수정하기 (기존의 글정보 확인) - GET
	@GetMapping(value = "/modify")
	public String modifyGET(@RequestParam("bno") int bno/*@ModelAttribute*/,Model model) throws Exception{
		logger.debug("modifyGET() 실행 ");
		
		//전달정보 bno 저장
		logger.debug("bno :"+ bno);
		
		//서비스 -DAO 글정보 조회 동작
		BoardVO resultVO= bService.getBoard(bno);
		logger.debug("resultVO :{}",resultVO);
		
		//연결된 뷰페이지로 정보 전달 - Model객체
		model.addAttribute("resultVO", resultVO);
//		model.addAttribute("resultVO", bService.getBoard(bno)); 이렇게도 가능!!
		
		return"/board/modify";
	}
	
	//게시판 글 수정하기(글정보 수정) - POST
	@PostMapping(value = "/modify")
	public String modifyPOST(BoardVO vo, RedirectAttributes rttr) throws Exception{
		// RedirectAttributes반드시 redirect할때만 사용가능
		logger.debug("modifyPOST() 실행");
		
		//전달정보 저장
		logger.debug("수정할 내용 {}", vo);
		
		//서비스 - DAO 글내용을 수정
		bService.updateBoard(vo);
		
		//상태 정보 전달
		rttr.addFlashAttribute("msg", "updateOK"); //=>jsp페이지에서 var result = "${msg}";
		
		//페이지 이동(listALL)
		
		return"redirect:/board/listALL";
	}
	
	
	
	
}
