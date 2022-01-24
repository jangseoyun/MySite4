package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.BoardService;
import com.javaex.vo.BoardVo;

@Controller
@RequestMapping(value="/board")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	//--------board List 리스트--------------------------------------------------------
	@RequestMapping(value="/list", method = {RequestMethod.GET,RequestMethod.POST})
	public String list(Model model) {
		
		System.out.println("controller.boardList 접근");
		List<BoardVo> boardList = boardService.getList();
		
		model.addAttribute("boardList",boardList);
		
		return "board/list";
	}
	
	
	//--------board writeForm 글쓰기폼--------------------------------------------------------
	@RequestMapping(value="/writeForm", method = {RequestMethod.GET,RequestMethod.POST})
	public String writeForm() {
		
		System.out.println("controller.board.writeForm 접근");
		
		return "board/writeForm";
	}
	
	//--------board write 글등록--------------------------------------------------------
	@RequestMapping(value="/write", method = {RequestMethod.GET,RequestMethod.POST})
	public String write(@ModelAttribute BoardVo boardVo) {
		
		System.out.println("controller.board.write 접근");
		
		return "redirect:/board/list";
	}
}
