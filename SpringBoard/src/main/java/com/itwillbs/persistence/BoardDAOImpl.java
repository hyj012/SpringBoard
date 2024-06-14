package com.itwillbs.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.itwillbs.domain.BoardVO;

//하나의 빈의 객체로 등록돼서 외부에서 사용할 수 있도록 해준다.
@Repository
public class BoardDAOImpl implements BoardDAO {
	
	
	private static final Logger logger = LoggerFactory.getLogger(BoardDAOImpl.class);
	
	
	//디비 연결정보 -> 객체 주입을 통해서 사용(root-context안에 들어있는)
	@Inject
	private SqlSession sqlSession;
	
	//mapper의 NAMESPACE 정보 저장
	private static final String NAMESPACE="com.itwillbs.mapper.BoardMapper.";
	
	
	@Override
	public void create(BoardVO vo) throws Exception {
		logger.debug("연결된 mapper에 SQL 구문 실행");
		sqlSession.insert(NAMESPACE+"create", vo);
	}


	@Override
	public List<BoardVO> listALL() throws Exception {
		logger.debug("listALL()실행");
		//mapper에 설정된 SQL 구문을 실행
		
		return sqlSession.selectList(NAMESPACE+"listALL");
	}

	
	//글조회 카운트
	@Override
	public void updateReadCnt(int bno) throws Exception {
		logger.debug("updatReadCnt(int bno) 실행");
		
		sqlSession.update(NAMESPACE+"updateReadCnt", bno);
		
	}


	@Override
	public BoardVO getBoard(int bno) throws Exception {
		return sqlSession.selectOne(NAMESPACE+"getBoard",bno);
	}
	
	
	
	
	
	
}
