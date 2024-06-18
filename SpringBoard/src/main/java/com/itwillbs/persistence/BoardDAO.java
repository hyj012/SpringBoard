package com.itwillbs.persistence;

import java.util.List;

import com.itwillbs.domain.BoardVO;

public interface BoardDAO {
	
	//게시판에 필요한 동작 만들기
	
	//글을 작성하는 동작
	public void create(BoardVO vo) throws Exception;
	
	//게시판 리스트(ALL)
	public List<BoardVO> listALL() throws Exception;
	
	//글 조회
	public void updateReadCnt(int bno) throws Exception;
	
	// 특정 bno의 글정보 가져오기
		public BoardVO getBoard(int bno) throws Exception;
	
	//특정 글 정보 수정하기
	public void UpdateBoard(BoardVO vo)throws Exception; 	
	//특정 글 정보 삭제
	public void deleteBoard(int bno)throws Exception; 
	
		
}
