package com.bili.diushoujuaner.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.bili.diushoujuaner.common.session.CustomSessionManager;

public class CustomSessionInterceptor implements HandlerInterceptor {
	
	public String[] allowUrls;//还没发现可以直接配置不拦截的资源，所以在代码里面来排除  
    
    public void setAllowUrls(String[] allowUrls) {  
        this.allowUrls = allowUrls;  
    }  

	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object arg2) throws Exception {
		
		String requestUrl = request.getRequestURI().replace(request.getContextPath(), "");    
        for(String url : allowUrls) {    
        	if(requestUrl.contains(url)) {    
        		return true;    
            }
        }
        
        String accessToken = null;
        if(requestUrl.contains("/home")){
        	Cookie[] cookies = request.getCookies();
        	for(int i = 0; cookies!= null && i <cookies.length; i++){
        		if(cookies[i].getName().equals("AccessToken")){
        			accessToken = cookies[i].getValue();
        		}
        	}
        }else{
        	accessToken = request.getHeader("AccessToken");
        }
        
        if(accessToken == null || CustomSessionManager.getCustomSession(accessToken) == null){
        	response.sendRedirect("login");
        	return false;
        }else{
        	return true;
        }
	}

}
