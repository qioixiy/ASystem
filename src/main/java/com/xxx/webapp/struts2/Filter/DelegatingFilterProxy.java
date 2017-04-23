package com.xxx.webapp.struts2.Filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.dispatcher.FilterDispatcher;
import org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionContext;


public class DelegatingFilterProxy extends FilterDispatcher {
	private org.slf4j.Logger logger = LoggerFactory.getLogger(DelegatingFilterProxy.class);
	final static private List<String> IGNORE_SESSION_CHECKING_LIST = Arrays.asList("/login.html");
 
	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)res;
		
		ServletContext servletContext = getServletContext();
 
		String servletPath = request.getServletPath();
		logger.debug("doFilter: {},{}", servletPath,
				IGNORE_SESSION_CHECKING_LIST.indexOf(servletPath));
  
		HttpSession session = request.getSession();
		Object userName = session.getAttribute("userName");
		if( userName == null && IGNORE_SESSION_CHECKING_LIST.indexOf(servletPath) == -1 ){
			response.sendRedirect("login.html");
		} else {
			FreeMarkerRender tFreeMarkerRender = new FreeMarkerRender(servletContext);
			String path = request.getServletPath();
			
			Map<String, Object> dataMap = new HashMap<String, Object>();
			dataMap.put("userName", userName);
			if (!tFreeMarkerRender.render(path.substring(1), null, response.getWriter())) {
				super.doFilter(req, res, chain);	
			}
		}
	}
 
 
}