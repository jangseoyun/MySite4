package com.javaex.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.GuestbookService;
import com.javaex.vo.GuestbookVo;

@Controller
@RequestMapping("/api/guest")
public class ApiGuestbookController {
	
	@Autowired
	private GuestbookService guestbookService;
	
	//------------addList 등록폼-----------------------------------
	@RequestMapping("/addList")
	public String addList() {
		
		System.out.println("controller.api/guest.addList 접근");
		
		return "aGuestbook/addList";
	}
	
	//------------list 리스트 화면-----------------------------------
	@ResponseBody //응답문서의 바디에 붙여서 보내라
	@RequestMapping("/list")
	public List<GuestbookVo> list() {
		
		System.out.println("controller.api/guest.list 접근");
		List<GuestbookVo> guestbookList = guestbookService.getList();
		System.out.println(guestbookList);
		
		//json으로 데이터를 응답문서로 바로 보냄(return값 맞춰줄 것 )
		return guestbookList;
	}
	
	//------------write 글등록-----------------------------------
	@ResponseBody
	@RequestMapping("/write")
	public GuestbookVo write(@ModelAttribute GuestbookVo guestbookVo) {
		
		System.out.println("controller.api/guest.write 접근");
		//System.out.println(guestbookVo);
		
		//저장하고 저장된값 리턴
		GuestbookVo gVo = guestbookService.addGuestResultVo(guestbookVo);
		System.out.println(gVo);
		
		return gVo;
	}
	
	//------------write2 글등록-----------------------------------
	@ResponseBody
	@RequestMapping("/write2")
	//json문자열로 온것이다. java언어로 온것이 아님. 파라미터 않씀
	public GuestbookVo write2(@RequestBody GuestbookVo guestbookVo) {
		
		System.out.println("controller.api/guest.write2 접근");
		System.out.println(guestbookVo);
		
		//저장하고 저장된값 리턴
		GuestbookVo gVo = guestbookService.addGuestResultVo(guestbookVo);
		System.out.println(gVo);
		
		return gVo;
	}

	
	//------------remove 삭제-----------------------------------
	@ResponseBody
	@RequestMapping("/remove")
	public String remove(@ModelAttribute GuestbookVo guestbookVo) {
		
		System.out.println("controller.api/guest.remove 접근");
		System.out.println(guestbookVo);
		
		String result = guestbookService.removeGuest(guestbookVo);
		//"success" "fail"
		System.out.println(result);
		
		
		return result;
	}


}
