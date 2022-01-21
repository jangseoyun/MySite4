package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value="/user")
public class UserController {
	
	//필드
	@Autowired
	private UserDao userDao;

	//----------loginForm 로그인폼-----------------------------------
	@RequestMapping(value="/loginForm", method = {RequestMethod.GET,RequestMethod.POST})
	public String loginForm() {
		
		System.out.println("loginForm.Controller 접근");
			
		return "user/loginForm";
	}
	
	//----------login 로그인-----------------------------------
	@RequestMapping(value="/login", method = {RequestMethod.GET,RequestMethod.POST})
	public String login(@ModelAttribute UserVo userVo) {
		
		System.out.println("login.Controller 접근");
		
		UserVo authUser = userDao.getUser(userVo);
		System.out.println("login : "+authUser);
		
		return "";
	}
	
	//----------joinForm 회원가입폼-----------------------------------
	@RequestMapping(value="/joinForm", method = {RequestMethod.GET,RequestMethod.POST})
	public String joinForm() {
		
		
		return "";
	}
	
	//----------joinOk 회원가입 성공-----------------------------------
	
}
