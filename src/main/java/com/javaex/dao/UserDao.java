package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {
	
	@Autowired
	private SqlSession sqlsession;

	//한명의 유저정보 가져오기---------------------------------------
	public UserVo getUser(UserVo userVo) {
		System.out.println("dao.getUser 접근");
		System.out.println(userVo);
		
		UserVo authUser = sqlsession.selectOne("user.getUser", userVo);
		
		return authUser;
	
	}
	
	//한명의 유저정보 가져오기---------------------------------------
	public void joinInsert(UserVo userVo) {
		
		System.out.println("dao.joinInsert 접근");
		
		sqlsession.insert("user.userInsert",userVo);
	}
	
	//=========================================================
	//아이디 중복 체크----------------------------------------------
	public int idSelect(String userId) {
		
		System.out.println("dao.idSelect 접근");
		
		return sqlsession.selectOne("user.idSelect",userId);
		
	}
	
}
