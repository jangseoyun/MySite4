package com.javaex.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;
import com.javaex.vo.RboardVo;

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
	
	//---------board list2 (리스트+페이징)--------------------------------
	public Map<String,Object> getList2(int crtPage) {
		
		System.out.println("service.board.getList2 접근");
		
		
		//1) ★리스트 가져오기
		//#페이지당 글 개수
		int listCnt = 10;
		
		//현재페이지 처리(3항 연산자) --> 페이지 0이하 접근 제한
		 crtPage = (crtPage>0) ? crtPage : (crtPage=1);
		
		/*if(crtPage <= 0){
			crtPage = 1;
		}*/

		//#페이지 시작글 번호
		int startRnum = (crtPage-1)*listCnt	+1;
		//#페이지 마지막 글 번호
		int endRnum = (startRnum + listCnt) -1;
		//-->vo로 묶어서 보내도되고, dao에서 묶어도됨
		List<BoardVo> boardList = boardDao.getList2(startRnum,endRnum);
	
		//2) ★페이지 카운트 가져오기-----------------
		//전체 글갯수 가져오기
		int totalCnt = boardDao.selectTotal();
		System.out.println("totalCnt:"+totalCnt);
		
		//페이지당 버튼 갯수
		int pageBtnCount = 5;
		
		//마지막 버튼 번호(올림해서 한 그룹에 동일한 값이 나오도록 만들어준다)
		int endPageBtnNo = (int)(Math.ceil( crtPage/(double)pageBtnCount))*pageBtnCount;
		
		//시작 버튼 번호
		int startPageBtnNo = endPageBtnNo - (pageBtnCount-1); 
		
		//다음 화살표 유무
		boolean next = false;
		if(endPageBtnNo*listCnt < totalCnt) {
			next = true;
		}else {//다음 화살표가 안보이면 마지막 버튼값을 다시 계산
			endPageBtnNo = (int)Math.ceil(totalCnt/(double)listCnt);
		}
		
		//이전 화살표 유무
		boolean prev = false;
		if(startPageBtnNo != 1) {
			prev = true;
		}
		
		//3) ★포장---------------------------------
		Map<String,Object> pMap = new HashMap<String,Object>();
		pMap.put("prev", prev);
		pMap.put("startPageBtnNo", startPageBtnNo);
		pMap.put("endPageBtnNo", endPageBtnNo);
		pMap.put("next", next);
		pMap.put("boardList", boardList);
		
		return pMap;
		
	}

	//---------board write 게시글 작성--------------------------------
	public int boardInsert(BoardVo boardVo) {
		
		System.out.println("service.board.boardInsert 접근");
		
		/*
		//페이징 데이터 임의 추가 123개
		for(int i = 1; i<=123; i++) {
			boardVo.setTitle(i+"번째글 제목입니다.");
			boardVo.setContent(i+"번째글 내용입니다.");
			boardVo.setHit(0);
			boardVo.setUserNo(1);
			
			boardDao.boardInsert(boardVo);
		}*/

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
	
	//=====ajax 댓글 게시판========================================================
	
	//---------replyboard List 댓글 게시판 리스트--------------------------------
	public List<RboardVo> replySelect() {
		
		System.out.println("service.board.replySelect 접근");
		return boardDao.replyList();
		
	}
	
	//---------replyboard write 댓글게시판 글쓰기--------------------------------
	public int replyInsert(RboardVo rboardVo) {
		
		System.out.println("service.board.replayInsert 접근");
		return boardDao.replyInsert(rboardVo);
		
	}
	
	//=====페이징========================================================
	
	
}
