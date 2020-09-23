package com.koreait.matzip;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginCkeckInterceptor extends HandlerInterceptorAdapter{
	@Override 
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception{
		
		String uri = request.getRequestURI();
		System.out.println("uri : " + uri);
		String[] uriArr = uri.split("/");
		
		if(uriArr[1].equals("res")) {	//리소스 (css, img, js)
			return true;
		} else if(uriArr.length < 3) {	//주소 이상함
			return false;
		}
		
		System.out.println("인터셉터!");
		boolean isLogout = SecurityUtils.isLogout(request);
		
		System.out.println("uriArr.length : " + uriArr.length);
		
		switch(uriArr[1]) {
		case ViewRef.URI_USER:
			switch(uriArr[2]) {
			case "login": case "join":
				if(!isLogout) {
					response.sendRedirect("rest/map");
					return false;
				}
			}
		
		case ViewRef.URI_REST:
			switch(uriArr[2]) {
			case "reg":
				if(isLogout) {
					response.sendRedirect("/user/login");
					return false;
				}
			}
		}
		
		return true;
	}
}
