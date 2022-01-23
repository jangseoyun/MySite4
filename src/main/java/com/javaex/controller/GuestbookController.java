package com.javaex.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.dao.GuestbookDao;
import com.javaex.vo.GuestbookVo;

@Controller
@RequestMapping(value="/guest")
public class GuestbookController {

	@Autowired
	private GuestbookDao guestbookDao;
	
	//------------addList 등록폼-----------------------------------
	@RequestMapping(value="/addList", method = {RequestMethod.GET,RequestMethod.POST})
	public String addList(Model model) {
	
		System.out.println("guestController.addList 접근");
		List<GuestbookVo> guestbookList = guestbookDao.getList();
		System.out.println(guestbookList);
		
		//컨트롤러가 --> DS 데이터를 보낸다
		model.addAttribute("guestbookList",guestbookList);
		
		return "guestbook/addList";
	}
	
	//------------add 등록----------------------------------------
	@RequestMapping(value="/add", method = {RequestMethod.GET,RequestMethod.POST})	
	public String add(@ModelAttribute GuestbookVo guestbookVo) {
		
		System.out.println("guestController.add 접근");
		
		guestbookDao.guestbookInsert(guestbookVo);
		
		return "redirect:/guest/addList";
	}
	
	//------------deleteForm 삭제폼----------------------------------
	@RequestMapping(value="/deleteForm", method = {RequestMethod.GET,RequestMethod.POST})
	public String deleteForm() {
		
		System.out.println("guestController.deleteForm 접근");
	
		return "guestbook/deleteForm";
	}
	
	//------------delete 삭제----------------------------------------
	@RequestMapping(value="/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public String delete(@RequestParam("no") int no,
					  @RequestParam("password") String password) {
		
		System.out.println("guestController.delete 접근");
		
		guestbookDao.guestbookDelete(no, password);
		
		return "redirect:/guest/addList";
	}
	
	
	
}
