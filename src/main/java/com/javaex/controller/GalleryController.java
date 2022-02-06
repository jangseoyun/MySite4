package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.GalleryService;
import com.javaex.vo.GalleryVo;

@Controller
@RequestMapping("/gallery")
public class GalleryController {
	
	@Autowired
	private GalleryService galleryService;

	//-----갤러리 리스트폼------------------------------------------
	@RequestMapping("/list")
	public String galleryListForm(Model model) {
		
		System.out.println("Conteroller.gallery.listForm 접근");
		
		List<GalleryVo> imgList = galleryService.gallerySelect();
		model.addAttribute("lmgList",imgList);
		
		return "gallery/list";
	}
	
	
	//-----갤러리 이미지 등록------------------------------------------
	@RequestMapping("/upload")
	public String galleryUpload(@RequestParam MultipartFile file) {
		
		System.out.println("Conteroller.gallery.galleryUpload 접근");
		
		
		return "redirect:gallery/list";
	}
	
	
	
}
