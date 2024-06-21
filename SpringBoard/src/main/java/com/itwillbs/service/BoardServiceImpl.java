package com.itwillbs.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.itwillbs.domain.BoardVO;
import com.itwillbs.domain.Criteria;
import com.itwillbs.persistence.BoardDAO;

@Service
public class BoardServiceImpl implements BoardService {
	
	
	private static final Logger logger = LoggerFactory.getLogger(BoardServiceImpl.class);
	
	@Inject
	private BoardDAO bdao;
	
	@Override
	public void regist(BoardVO vo) throws Exception {
		logger.debug("연결된 DAO 메서드를 호출");
		
		bdao.create(vo);
		
	}

	
	@Override
	public List<BoardVO> listALL() throws Exception {
		logger.debug("게시판 목록 조회");
		
		return bdao.listALL();
	}

	//글 조회수 1증가
	@Override
	public void updateReadCnt(int bno) throws Exception {
		logger.debug("updateReadCnt(int bno) 실행");
		
		bdao.updateReadCnt(bno);
		
	}


	//특정 bno글 정보 가져오기
	@Override
	public BoardVO getBoard(int bno) throws Exception {
		
		return bdao.getBoard(bno);
	}

	
	//특정 bno 글정보 수정
	@Override
	public void updateBoard(BoardVO vo) throws Exception {
		logger.debug("updateBoard(BoardVO vo) 실행");
		bdao.UpdateBoard(vo);
		
	}

	//특정 글정보 삭제
	@Override
	public void deleteBoard(int bno) throws Exception {
		logger.debug("deleteBoard(BoardVO vo) 실행");
		bdao.deleteBoard(bno);
		
	}

	//페이징처리
	@Override
	public List<BoardVO> listPage(Criteria cri) throws Exception {
		logger.debug("listPage(Criteria cri) 실행");
		return bdao.listPage(cri);
	}


	@Override
	public int getTotalCount() throws Exception {
		
		return bdao.getTotalCount();
	}
	
	
	
	
	
	
	
	
	

	
	
}
