package kr.co.itcen.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.itcen.jblog.repository.UserDao;
import kr.co.itcen.jblog.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	// 회원가입
	public void join(UserVo vo) {
		userDao.insert(vo);
	}
	
	// id 중복검사
	public Boolean existUser(String email) {
		return userDao.get(email) != null;
	}

}
