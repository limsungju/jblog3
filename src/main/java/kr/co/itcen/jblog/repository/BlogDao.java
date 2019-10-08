package kr.co.itcen.jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.itcen.jblog.vo.BlogVo;

@Repository
public class BlogDao {
	@Autowired
	private SqlSession sqlSession;

	public Boolean createBlog(BlogVo blogVo) {
		int count = sqlSession.insert("blog.insertBlog", blogVo);
		return count == 1;
	}
	
}
