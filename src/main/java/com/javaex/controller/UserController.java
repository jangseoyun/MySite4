package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value="/user")
public class UserController {
	
	//필드
	@Autowired
	private UserService userService;

	//----------loginForm 로그인폼-----------------------------------
	@RequestMapping(value="/loginForm", method = {RequestMethod.GET,RequestMethod.POST})
	public String loginForm() {
		
		System.out.println("loginForm.Controller 접근");
			
		return "user/loginForm";
	}
	
	//----------login 로그인-----------------------------------
	@RequestMapping(value="/login", method = {RequestMethod.GET,RequestMethod.POST})
	public String login(@ModelAttribute UserVo userVo, HttpSession session) {
		
		System.out.println("login.Controller 접근");
		
		UserVo authUser = userService.login(userVo);
		System.out.println("login : "+authUser);
		
		if(authUser != null) {//로그인 성공
			//세션에 저장
			session.setAttribute("authUser", authUser);
			
			//리다이렉트 메인
			return "redirect:/index";
			
		}else {//로그인 실패 
			
			//리다이렉트
			return "redirect:/user/loginForm?result=fail";
		}
	}	
	
	//----------logout 로그아웃-----------------------------------
	@RequestMapping(value="/logout", method = {RequestMethod.GET,RequestMethod.POST})	
	public String logout(HttpSession session) {
		
		System.out.println("logout.Controller 접근");
		
		//세션의 정보 삭제
		session.removeAttribute("authUser");
		session.invalidate();
		
		return "redirect:/index";
	}
		
		
	//----------joinForm 회원가입폼-----------------------------------
	@RequestMapping(value="/joinForm", method = {RequestMethod.GET,RequestMethod.POST})
	public String joinForm() {
		
		System.out.println("joinForm.Controller 접근");
		
		return "user/joinForm";
	}
	
	//----------join-----------------------------------
	@RequestMapping(value="/join", method = {RequestMethod.GET,RequestMethod.POST})
	public String join(@ModelAttribute UserVo userVo) {
		
		System.out.println("join.Controller 접근");		
		userService.join(userVo);

		return "user/joinOk";
	}
	
	
	
	
}
