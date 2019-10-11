package kr.co.itcen.jblog.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kr.co.itcen.jblog.service.BlogService;
import kr.co.itcen.jblog.service.CategoryService;
import kr.co.itcen.jblog.service.FileuploadService;
import kr.co.itcen.jblog.service.PostService;
import kr.co.itcen.jblog.vo.BlogVo;
import kr.co.itcen.jblog.vo.PostVo;

@Controller
@RequestMapping("/{id:(?!assets)(?!images).*}")
public class BlogController {
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private FileuploadService fileuploadService;
	
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
	
	// 블로그 관리 페이지
	@RequestMapping("/admin")
	public String blogAdmin(@PathVariable String id,
			@RequestParam(value="admin_no", required=false, defaultValue="1") int admin_no,
			Model model) {
		
		BlogVo blogVo = blogService.getInfo(id);
		
		model.addAttribute("blogInfo", blogVo);
		model.addAttribute("admin_no", admin_no);
		
		// 카테고리 관리 페이지
		if(admin_no ==2 ) {
			// 블로그 카테고리 목록 가져오기
			
			return "blog/blog-admin-category";
		}
		
		// 포스트 관리 페이지
		if(admin_no == 3) {
			// 
			
			return "blog/blog-admin-write";
		}
		
		return "blog/blog-admin-basic";
	}
	
	// 블로그 기본 설정 변경
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String blogUpdate(@PathVariable String id,
			@RequestParam(value="title", defaultValue="") String title,
			@RequestParam(value="logo-file") MultipartFile multipartFile,
			Model model) {
		
		String logoUrl = fileuploadService.restore(multipartFile);
		
		BlogVo blogVo = new BlogVo();
		
		blogVo.setId(id);
		blogVo.setTitle(title);
		blogVo.setLogo(logoUrl);
		
		// 블로그 정보 변경
		blogService.update(blogVo);
		
		
		
		return "redirect:/"+id;
	}
	
}
