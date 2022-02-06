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
	
}
