package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {

	//-----파일 업로드 폼------------------------------------------
	public String restore(MultipartFile file) {
		
		System.out.println("Service.restore() 접근");
		System.out.println(file.getOriginalFilename());
		
		String saveDir = "C:\\javaStudy\\upload";
		
		//파일을 하드디스크에 저장(운영내용)
		
		//--------준비단계(관련정보추출)--------------------------
		//1. 원본파일 이름
		String orgName = file.getOriginalFilename();
		
		//2. 확장자(내보낼때)
		//index이후의 값을 가져와라 
		String exName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));

		//3+2 저장파일이름 +겹치지 않도록 현재올린 시간까지 올려준다
		String saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exName;
		System.out.println(saveName);
		
		//4. 파일패스 생성(파일 저장 위치)
		//경로가 바뀔 수 있기 때문에 변수로 뺀다
		String filePath = saveDir+"\\"+saveName;
		
		//5. 파일 사이즈
		//리턴형이 long형이다
		long fileSize = file.getSize();
		
		//------★파일 저장(업로드)-------------------------
		//하드디스크 저장
		try {
			byte[] fileDate = file.getBytes();
			OutputStream out = new FileOutputStream(filePath);
			BufferedOutputStream bout = new BufferedOutputStream(out);
			
			bout.write(fileDate);
			bout.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		//DB에 저장 과제
		
		//파일 이름을 리턴해준다.
		return saveName;
		
	}
}
