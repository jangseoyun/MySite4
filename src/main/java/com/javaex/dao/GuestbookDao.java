package com.javaex.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestbookVo;

@Repository
public class GuestbookDao {

	// 필드
	@Autowired
	private SqlSession sqlsession;

	// 생성자

	// 메소드 g,s

	// 메소드 일반
	// ------------List-----------------------------
	public List<GuestbookVo> getList() {
		
		System.out.println("guestDao.addList 접근");
		List<GuestbookVo> guestList = sqlsession.selectList("guestbook.list");
		
		return guestList;
	}
	
	// ------------insert-----------------------------

	public int guestbookInsert(GuestbookVo guestbookVo) {
		
		System.out.println("guestDao.insert 접근");
		int count = sqlsession.insert("guestbook.insert",guestbookVo);
		
		return count;
	}
	
	// ------------delete-----------------------------

	public int guestbookDelete(int no, String password) {
		
		System.out.println("guestDao.delete 접근");
		
		Map<String, Object> guestDeleteMap = new HashMap<String, Object>();
		guestDeleteMap.put("no",no);
		guestDeleteMap.put("password", password);
		
		int count = sqlsession.delete("guestbook.delete", guestDeleteMap);
		
		return count;
	}

	
	//========ajax 방명록=====================================
	
	// ------------insert&selectKey-----------------------------
	public int insertSelectKey(GuestbookVo guestbookVo) {
		
		System.out.println("guestDao.insertSelectKey 접근");
		System.out.println(guestbookVo); //no없음
		
		
		System.out.println(guestbookVo); //no들어가있음
		
		return sqlsession.insert("guestbook.insertSelectKey",guestbookVo);
	}
	
	// ------------selectOne--방명록 글 한개 가져오기---------------------------
	public GuestbookVo selectGuest(int no) {
		System.out.println("guestDao.selectGuest 접근");
		
		return sqlsession.selectOne("guestbook.selectByNo", no);
	}

	

}
