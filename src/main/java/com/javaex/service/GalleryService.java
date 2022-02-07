package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.GalleryDao;
import com.javaex.vo.GalleryVo;

@Service
public class GalleryService {
	
	@Autowired
	private GalleryDao galleryDao;
	
	String saveDir = "C:\\javaStudy\\upload"; 
	
	//-----갤러리 리스트폼------------------------------------------
	public List<GalleryVo> gallerySelect() {
		
		System.out.println("Service.galleryList 접근");

		return galleryDao.gallerySelect();
	}
	
	
	//-----등록--------------------------------------------------
	public int fileInsert(MultipartFile file, GalleryVo galleryVo) {
		
		System.out.println("Service.fileInsert 접근");
		
		//준비단계(관련정보추출)-----------------------------------
		//1) 원본파일명
		String orgName = file.getOriginalFilename();
		
		//2) 확장자
		String exName = orgName.substring(orgName.lastIndexOf("."));
		
		//3) 저장파일이름
		String saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exName;
		
		//4) 파일패스 생성(파일 저장 위치)
		String filePath = saveDir+"\\"+saveName;
		
		//4) 파일 사이즈
		long fileSize = file.getSize();
		
		//5) 필요한 데이터 넣기
		galleryVo.setOrgName(orgName);
		galleryVo.setSaveName(saveName);
		galleryVo.setFilePath(filePath);
		galleryVo.setFileSize(fileSize);
		
		//하드디스크에 저장
		try {
			byte[] fileDate = file.getBytes();
			OutputStream out = new FileOutputStream(filePath);
			BufferedOutputStream bout = new BufferedOutputStream(out);
			
			bout.write(fileDate);
			bout.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		//DB에 저장-----------------------------------
		return galleryDao.fileInsert(galleryVo);
	}
	
	//-----해당 이미지 가져오기-----------------------------------------------
	public GalleryVo ImgVoSelect(int no) {
		
		System.out.println("Service.ImgVoSelect 접근");
		return galleryDao.ImgVoSelect(no);
	}
	
}
