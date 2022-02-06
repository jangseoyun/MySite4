package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.GalleryDao;
import com.javaex.vo.GalleryVo;

@Service
public class GalleryService {
	
	@Autowired
	private GalleryDao galleryDao;
	
	//-----갤러리 리스트폼------------------------------------------
	public List<GalleryVo> gallerySelect() {
		
		System.out.println("Service.galleryList 접근");

		return galleryDao.gallerySelect();
	}
	
	
	//-----등록--------------------------------------------------
	public void fileInsert() {
		
	}
}
