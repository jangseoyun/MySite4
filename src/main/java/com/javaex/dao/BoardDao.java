package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;

@Repository
public class BoardDao {

	@Autowired
	private SqlSession sqlsession;
	
	//------게시판 리스트--------------------------------------
	public List<BoardVo> getList() {
		
		System.out.println("dao.board.getList 접근");
		List<BoardVo> boardList = sqlsession.selectList("board.getList");
		
		return boardList;
	}
	
	//------게시글 작성--------------------------------------
	public int boardInsert(BoardVo boardVo) {
		
		System.out.println("dao.board.boardInsert 접근");
		return sqlsession.insert("board.boardInsert",boardVo);
	}
	
	//------게시글 삭제--------------------------------------
	public int boardDelete(int bNo) {
		
		System.out.println("dao.board.boardDelete 접근");
		return sqlsession.delete("board.boardDelete",bNo);
	}
	
	//------게시글 읽기--------------------------------------
	public BoardVo boardSelect(int bNo){
		
		System.out.println("dao.board.boardSelect 접근");
		BoardVo boardVo = sqlsession.selectOne("board.boardSelect", bNo);
		
		return boardVo;

	}
	
	//------조회수 업로드--------------------------------------	
	public int viewsUpdate(int bNo) {
		
		System.out.println("dao.board.viewsUpdate 접근");
		return sqlsession.update("board.viewsUpdate",bNo);
	}
	
	//------수정 게시글 읽기--------------------------------------
	public BoardVo modifySelect(int bNo){
		
		System.out.println("dao.board.modifySelect 접근");
		BoardVo boardVo = sqlsession.selectOne("board.boardSelect", bNo);
		
		return boardVo;

	}
	
	//------게시글 수정--------------------------------------
	public int modify(BoardVo boardVo) {
		
		System.out.println("dao.board.modify 접근");
		return sqlsession.update("board.boardModify", boardVo);
		
	}
	
}
