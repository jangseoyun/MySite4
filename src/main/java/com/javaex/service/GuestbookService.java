package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.GuestbookDao;
import com.javaex.vo.GuestbookVo;

@Service
public class GuestbookService {

	@Autowired
	private GuestbookDao guestbookDao;
	
	//---------addList-------------------------------------
	public List<GuestbookVo> getList() {
		
		System.out.println("service.getList 접근");
		List<GuestbookVo> guestbookList = guestbookDao.getList();
		
		return guestbookList;
	}
	
	//---------add-------------------------------------
	public int guestbookInsert(GuestbookVo guestbookVo) {
		
		System.out.println("service.insert 접근");
		
		return guestbookDao.guestbookInsert(guestbookVo);
	}
	
	
	//---------delete-------------------------------------
	public int guestbookDelete(int no, String password) {
		
		System.out.println("service.delete 접근");
		
		return guestbookDao.guestbookDelete(no, password);
	}
	
	//=========ajax 방명록====================================
	
	//---------방명록 글 저장 --> 저장글 리턴-------------------------------------
	public GuestbookVo addGuestResultVo(GuestbookVo guestbookVo) {
		
		System.out.println("service.addGuestResultVo 접근");
		//System.out.println(guestbookVo);
		//저장하기
		int count = guestbookDao.insertSelectKey(guestbookVo);
		
		//저장한 내용 가져오기
		int no = guestbookVo.getNo();
		//0x999
		GuestbookVo gVo= guestbookDao.selectGuest(no);
		return gVo;
	}
	
	
}
