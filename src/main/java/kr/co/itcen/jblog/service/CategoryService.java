package kr.co.itcen.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.itcen.jblog.repository.CategoryDao;
import kr.co.itcen.jblog.vo.CategoryVo;

@Service
public class CategoryService {
	@Autowired
	CategoryDao categoryDao;
	
	//블로그 카테고리 목록 가져오기
	public List<CategoryVo> getCategoryList(String id) {
		List<CategoryVo> categoryList = categoryDao.getCategoryList(id);
		return categoryList;
	}
	
	// 카테고리 작성 * 리스트 반환
	public List<CategoryVo> write(CategoryVo categoryVo) {
		// 카테고리 작성
		categoryDao.write(categoryVo);
		
		// 블로그 카테고리 목록 가져오기
		return categoryDao.getCategoryList(categoryVo.getId());
	}
	
	// 카테고리 삭제 * 리스트 반환
	public List<CategoryVo> delete(CategoryVo categoryVo) {
		// 카테고리 삭제
		categoryDao.delete(categoryVo);
		
		// 블로그 카테고리 목록 가져오기
		return categoryDao.getCategoryList(categoryVo.getId());
	}
	
	// 카테고리명 가져오기
	public List<CategoryVo> getCategoryName(String id) {
		List<CategoryVo> categoryList = categoryDao.getCategoryName(id);
		return categoryList;
	}

	
	
}
