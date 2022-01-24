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
		int count = guestbookDao.guestbookInsert(guestbookVo);
		
		return count;
	}
	
	
	//---------delete-------------------------------------
	public int guestbookDelete(int no, String password) {
		
		System.out.println("service.delete 접근");
		int count = guestbookDao.guestbookDelete(no, password);
		
		return count;
	}
	
	
}
