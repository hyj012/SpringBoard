package com.itwillbs.persistence;

import java.util.List;

import com.itwillbs.domain.BoardVO;

public interface BoardDAO {
	
	//게시판에 필요한 동작 만들기
	
	//글을 작성하는 동작
	public void create(BoardVO vo) throws Exception;
	
	//게시판 리스트(ALL)
	public List<BoardVO> listALL() throws Exception;
	
	
}
