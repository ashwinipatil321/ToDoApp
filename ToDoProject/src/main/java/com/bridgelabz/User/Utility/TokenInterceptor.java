/*package com.bridgelabz.User.Utility;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class TokenInterceptor implements HandlerInterceptor {
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {

		System.out.println("intercepted : "+request.getHeader("token"));
		
		int userId =  Token.verify(request.getHeader("acessToken"));
		if (userId == 0) {
			response.setStatus(511);
			return false;
		}
		System.out.println("jhdewdjew wdjhwe wuyew--------> "+userId);
		request.setAttribute("userId", userId);
		return true;
	}
}


*/