package kr.co.itcen.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.itcen.jblog.repository.CategoryDao;

@Service
public class CategoryService {
	@Autowired
	CategoryDao categoryDao;

	
	
}
