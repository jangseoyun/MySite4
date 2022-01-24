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
		
		System.out.println("dao.baord.getList 접근");
		List<BoardVo> boardList = sqlsession.selectList("board.getList");
		
		return boardList;
	}
	
	//------게시글 작성--------------------------------------
	public void boardInsert() {
		
	}
	
	
}
