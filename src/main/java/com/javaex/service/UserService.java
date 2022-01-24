package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	//---------login 로그인-------------------------------------
	public UserVo login(UserVo userVo) {
		
		System.out.println("service.login 접근");
		
		UserVo authUser = userDao.getUser(userVo);
		return authUser;
	}

	//---------join 로그인-------------------------------------
	public void join(UserVo userVo) {
		
		System.out.println("service.join 접근");
		
		userDao.joinInsert(userVo);
		
	}
	
	//---------join 로그인-------------------------------------
	
	
	
}
