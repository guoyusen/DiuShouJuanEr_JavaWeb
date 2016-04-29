package com.bili.diushoujuaner.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.bili.diushoujuaner.common.CommonUtils;
import com.bili.diushoujuaner.common.ConstantUtils;
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
        
        String accessToken = null, deviceType = null;
        if(requestUrl.contains("/home")){
        	Cookie[] cookies = request.getCookies();
        	for(int i = 0; cookies!= null && i <cookies.length; i++){
        		if(cookies[i].getName().equals("AccessToken")){
        			accessToken = cookies[i].getValue();
        			break;
        		}
        	}
        	//进入home页面，判断权限
        	if(accessToken == null || CustomSessionManager.getCustomSession(accessToken) == null){
            	response.sendRedirect("login");
            	return false;
            }
        }else{
        	accessToken = request.getHeader("AccessToken");
        	deviceType = request.getHeader("Device-Type");
        	if(accessToken == null 
        			|| CustomSessionManager.getCustomSession(accessToken) == null
        			|| deviceType == null
        			|| !CommonUtils.isDeviceTypelegal(deviceType)){
        		response.setContentType("text/html;charset=UTF-8");
        		response.getWriter().write(CommonUtils.getJSONStringFromObject(CommonUtils.getResponse(ConstantUtils.ERROR, "非法请求，请重新登录", null)));
            	
            	return false;
            }
        }
        return true;
	}

}
