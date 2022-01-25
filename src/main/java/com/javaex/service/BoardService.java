package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;

@Service
public class BoardService {
	
	@Autowired
	private BoardDao boardDao;

	//---------board list 게시판 리스트--------------------------------
	public List<BoardVo> getList() {
		
		System.out.println("service.board.getList 접근");
		List<BoardVo> boardList = boardDao.getList();
		
		return boardList;
		
	}
	
	//---------board write 게시글 작성--------------------------------
	public int boardInsert(BoardVo boardVo) {
		
		System.out.println("service.board.boardInsert 접근");
		return boardDao.boardInsert(boardVo);
		
	}
	
	//---------board delete 게시글 삭제--------------------------------
	public int boardDelete(int bNo) {
		
		System.out.println("service.board.boardDelete 접근");
		return boardDao.boardDelete(bNo);
	}
	
	//---------board read 게시글 읽기--------------------------------
	public BoardVo boardSelect(int bNo){

		System.out.println("service.board.boardSelect 접근");
		BoardVo boardVo = boardDao.boardSelect(bNo);
		
		return boardVo;
	}
	
	//---------board viewsUpdate 게시글 읽기--------------------------------
	public int viewsUpdate(int bNo) {
		
		System.out.println("service.board.viewsUpdate 접근");
		return boardDao.viewsUpdate(bNo);
	}
	
	//---------board modify 게시글 수정--------------------------------
	public int modify(BoardVo boardVo) {
		
		System.out.println("service.board.modify 접근");
		return boardDao.modify(boardVo);
	}
}
