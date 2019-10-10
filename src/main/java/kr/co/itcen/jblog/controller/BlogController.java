package kr.co.itcen.jblog.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.itcen.jblog.service.BlogService;
import kr.co.itcen.jblog.service.PostService;
import kr.co.itcen.jblog.vo.BlogVo;

@Controller
@RequestMapping("/{id:(?!assets).*}")
public class BlogController {
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private PostService postService;
	
	// 블로그 메인
	@RequestMapping({"","/{categoryno}","/{categoryno}/{postno}" })
	public String blogMain(@PathVariable String id, @PathVariable Optional<Long> categoryno, @PathVariable Optional<Long> postno, Model model ) {
		long categoryNo = categoryno.isPresent() ? categoryno.get() : 0; // 값이 있으면 값으로, 없으면 0으로 설정
		long postNo = postno.isPresent() ? postno.get() : 0;
		
		// 메인 컨텐츠 가져오기
		Map<String, Object> view = postService.view(id, categoryNo, postNo);
		
		// 블로그 메인 정보
		BlogVo blogVo = blogService.getInfo(id);
		
		if(blogVo == null || view == null) {
			return "user/join";
		}
		
		model.addAttribute("view", view);
		model.addAttribute("blogInfo", blogVo);
		
		return "blog/blog-main";
	}
	
}
