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
	@Autowired
	public List<BoardVo> getList() {
		
		System.out.println("service.board.getList 접근");
		List<BoardVo> boardList = boardDao.getList();
		
		return boardList;
		
	}
	
}
