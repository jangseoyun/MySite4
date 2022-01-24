package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
	public String writeForm(@RequestParam("uNo")int uNo) {
		
		System.out.println("controller.board.writeForm 접근");
		
		return "board/writeForm";
	}
	
	//--------board write 글등록--------------------------------------------------------
	@RequestMapping(value="/write", method = {RequestMethod.GET,RequestMethod.POST})
	public String write(@ModelAttribute BoardVo boardVo) {
		
		System.out.println("controller.board.write 접근");
		boardService.boardInsert(boardVo);
		
		return "redirect:/board/list";
	}
	
	//--------board delete 글 삭제--------------------------------------------------------
	@RequestMapping(value="/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public String delete(@RequestParam("bNo") int bNo) {
		
		System.out.println("controller.board.delete 접근");
		boardService.boardDelete(bNo);
		
		return "redirect:/board/list";
	}
	
	//--------board read 읽기--------------------------------------------------------
	@RequestMapping(value="/read", method = {RequestMethod.GET,RequestMethod.POST})
	public String read(@RequestParam("bNo") int bNo,
						Model model) {
		
		System.out.println("controller.board.read 접근");
		
		boardService.viewsUpdate(bNo);
		BoardVo boardListOne = boardService.boardSelect(bNo);

		model.addAttribute("boardListOne",boardListOne);
		
		return "board/read";
	}
	
	//--------board modifyForm 수정폼--------------------------------------------------------
	@RequestMapping(value="/modifyForm", method = {RequestMethod.GET,RequestMethod.POST})
	public String modifyForm(@RequestParam("bNo") int bNo,
								Model model) {
		
		System.out.println("controller.board.modifyForm 접근");
		BoardVo boardListOne = boardService.boardSelect(bNo);
		
		model.addAttribute("boardListOne",boardListOne);
		
		return "board/modifyForm";
	}
	
	//--------board modify 수정--------------------------------------------------------
	@RequestMapping(value="/modify", method = {RequestMethod.GET,RequestMethod.POST})
	public String modify(@ModelAttribute BoardVo boardVo) {
		
		System.out.println("controller.board.modify 접근");
		boardService.modify(boardVo);
		
		return "redirect:/board/list";
	}
	
}
