package kr.co.itcen.jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryDao {
	@Autowired
	private SqlSession sqlSession;
	
	// 카테고리 default(미분류) 생성
	public void defaultCategory(String id)
	{
		sqlSession.insert("category.defaultCategory",id);
	}
}
