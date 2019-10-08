package kr.co.itcen.jblog.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import kr.co.itcen.jblog.service.UserService;
import kr.co.itcen.jblog.vo.UserVo;

public class LoginInterceptor extends HandlerInterceptorAdapter {
	
	@Autowired
	private UserService userService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		UserVo userVo = new UserVo();
		userVo.setId(id);
		userVo.setPassword(password);
		
		// 어플리케이션 어디서든지 Spring Container(ApplicationContext)를 가져오는 방법
		// UserService를 그냥 new해서 생성하면 DI가 안되어 있기 때문에 NullPointException이 발생한다.
//		ApplicationContext appCtxt = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
//		UserService userService = appCtxt.getBean(UserService.class);
		
		UserVo authUser = userService.getUser(userVo);
		if(authUser == null) {
			response.sendRedirect(request.getContextPath() + "/user/login");
			return false;
		}
		
		// session처리
		HttpSession session = request.getSession(true);
		session.setAttribute("authUser", authUser);
		System.out.println("authUser : " + authUser);
		
		response.sendRedirect(request.getContextPath());
		return false;
	}
	
}
