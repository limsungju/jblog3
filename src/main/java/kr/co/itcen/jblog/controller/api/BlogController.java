package kr.co.itcen.jblog.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.itcen.jblog.dto.JSONResult;
import kr.co.itcen.jblog.service.CategoryService;
import kr.co.itcen.jblog.vo.CategoryVo;

@Controller("blogApiController")
@RequestMapping("/api/blog")
public class BlogController {
	@Autowired
	private CategoryService categoryService;
	
	// 카테고리 글 작성
	@ResponseBody
	@RequestMapping(value="/categoryWrite", method=RequestMethod.POST)
	public JSONResult categoryWrite(@ModelAttribute CategoryVo categoryVo) {
		// 카테고리 작성, 리스트
		List<CategoryVo> categoryList = categoryService.writeList(categoryVo);
		return JSONResult.success(categoryList);
	}
	
}
