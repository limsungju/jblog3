package kr.co.itcen.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.itcen.jblog.repository.BlogDao;
import kr.co.itcen.jblog.vo.BlogVo;

@Service
public class BlogService {
	@Autowired
	BlogDao blogDao;
	
	// 블로그 메인 정보
	public BlogVo getInfo(String id) {
		return blogDao.getInfo(id);
	}
	
	// 블로그 정보 변경
	public void update(BlogVo blogVo) {
		blogDao.update(blogVo);
	}
	
	

}
