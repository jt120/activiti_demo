/**
 * @author liuze
 *
 * Jan 28, 2014
 */
package com.jt.leave.utils;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class AuthFilter implements Filter {
	
	private static String username = "admin";

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		username = filterConfig.getInitParameter("username");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		String sname = (String) req.getSession().getAttribute("loginUser");
		if(sname==null) {
			String name = request.getParameter("username");
			if(name!=null) {
				username = name;
			}
			req.getSession().setAttribute("loginUser", username);
		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
	}

}
