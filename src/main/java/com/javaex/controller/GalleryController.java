package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
	public String galleryUpload(@RequestParam MultipartFile file, @ModelAttribute GalleryVo galleryVo) {
		
		System.out.println("Conteroller.gallery.galleryUpload 접근");

		System.out.println(file.getOriginalFilename());
		//System.out.println(galleryVo.getContent());
		
		galleryService.fileInsert(file,galleryVo);
		
		return "redirect:/gallery/list";
	}
	
	//-----해당 이미지 가져오기------------------------------------------
	@RequestMapping("/view")
	@ResponseBody
	public GalleryVo getView(@RequestParam("no") int no, Model model) {
		//no 받기
		System.out.println("Conteroller.gallery.getView 접근");
		System.out.println(no);
		
		GalleryVo getVo = galleryService.ImgVoSelect(no);
		System.out.println(getVo);
		model.addAttribute("getVo",getVo);
		
		return getVo;
	}
	
	//-----삭제------------------------------------------------------
	@RequestMapping("/delete")
	public int delete() {
		
		System.out.println("Conteroller.gallery.delete 접근");
		
		return 0;
	}
	
}
