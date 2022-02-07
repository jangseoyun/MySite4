package com.javaex.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;
import com.javaex.vo.RboardVo;

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
	
	//------#게시판 (리스트+페이징)ㄴ--------------------------------------
	public List<BoardVo> getList2(int startRnum, int endRnum) {
		System.out.println("boardDao/selectList2");
		System.out.println(startRnum + "," + endRnum);
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("startRnum", startRnum);
		map.put("endRnum", endRnum);
		
		List<BoardVo> boardList = sqlsession.selectList("board.getList2", map);
		//System.out.println(boardList);
		return boardList;
	}
		//------#게시판 (리스트+페이징)ㄴ--------------------------------------	
	public int selectTotal() {
		
		System.out.println("dao.board.selectTotal 접근");
		return sqlsession.selectOne("board.totalCnt");
	}
	
	
	//------게시글 등록--------------------------------------
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
	
	//------게시글 수정--------------------------------------
	public int modify(BoardVo boardVo) {
		
		System.out.println("dao.board.modify 접근");
		return sqlsession.update("board.boardModify", boardVo);
		
	}
	
	//======댓글게시판 dao===========================================================
	
	//------댓글게시판 리스트--------------------------------------
	public List<RboardVo> replyList(){
		
		System.out.println("dao.board.replyList 접근");
		
		List<RboardVo> replyBoardList = sqlsession.selectList("rboard.replySelect"); 
		
		return replyBoardList ;
	}
	
	//------댓글게시판 글쓰기--------------------------------------
	public int replyInsert(RboardVo rboardVo) {
		
		System.out.println("dao.board.replyInsert 접근");
		return sqlsession.insert("rboard.replyInsert",rboardVo);
		
	}
}
