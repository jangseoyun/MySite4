package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.FileService;

@Controller
@RequestMapping("/fileupload")
public class FileController {

	@Autowired
	private FileService fileService;
	
	//-----파일 업로드 폼------------------------------------------
	@RequestMapping("/form")
	public String form() {
		System.out.println("Controller.fileUpload.from() 접근");
		return "fileupload/form";
	}
	
	//-----파일 업로드 처리------------------------------------------
	@RequestMapping("/upload")
	public String upload(@RequestParam("file") MultipartFile file, Model model) {
		System.out.println("Controller.fileUpload.upload() 접근");
	
		System.out.println(file.getOriginalFilename());
		System.out.println(file.getSize());
		
		String saveName = fileService.restore(file);
		model.addAttribute("saveName",saveName);
		
		return "fileupload/result";
	}

	
}
