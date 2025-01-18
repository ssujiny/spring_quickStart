package com.springbook.biz.board.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbook.biz.board.BoardService;
import com.springbook.biz.board.BoardVO;

@Service("boardService")
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardDAOSpring boardDAO;
	
	//글 등록
	public void insertBoard(BoardVO vo) {
		
		/*
		if(vo.getSeq() == 0) {
			throw new IllegalArgumentException("0번 글은 등록할 수 없습니다.");
		}
		*/
		
		boardDAO.insertBoard(vo); // 100번 글 등록 성공
		boardDAO.insertBoard(vo); // Exception 발생
		
		// Transaction은 메소드 단위로 관리되므로 예외 발생시 insertBoard 메소드 작업결과는 모두 Rollback 처리된다.
	}
	
	//글 수정
	public void updateBoard(BoardVO vo) {
		boardDAO.updateBoard(vo);
	}
	
	//글 삭제
	public void deleteBoard(BoardVO vo) {
		boardDAO.deleteBoard(vo);
	}
	
	//글 상세조회
	public BoardVO getBoard(BoardVO vo) {
		return boardDAO.getBoard(vo);
	}
	
	//글 목록 조회
	public List<BoardVO> getBoardList(BoardVO vo){
		return boardDAO.getBoardList(vo);
	}

}