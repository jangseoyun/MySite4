package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GalleryVo;

@Repository
public class GalleryDao {
	
	@Autowired
	private SqlSession sqlSession;

	//-----갤러리 리스트폼------------------------------------------
	public List<GalleryVo> gallerySelect() {
		
		System.out.println("Dao.gallerySelect 접근");
		List<GalleryVo> imgList = sqlSession.selectList("gallery.listSelect");
		System.out.println(imgList);
		return imgList;
	}
	
	//-----이미지 컨텐츠 등록------------------------------------------
	public int fileInsert(GalleryVo galleryVo) {
		
		System.out.println("Dao.fileInsert 접근");
		int count = sqlSession.insert("gallery.fileInsert",galleryVo);
		System.out.println(count);
		return count;
	}
	
	//-----해당 이미지Vo 가져오기------------------------------------------
	public GalleryVo ImgVoSelect(int no) {
		
		System.out.println("Dao.ImgVoSelect 접근");
		
		GalleryVo getVo = sqlSession.selectOne("gallery.ImgVoSelect",no);
		return getVo;
	}
	
}
