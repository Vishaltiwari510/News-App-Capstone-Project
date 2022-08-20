package com.stackroute.capstone.apigateway.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

public class JwtFilter extends GenericFilterBean {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		  try {
		    HttpServletRequest httpRequest = (HttpServletRequest) request;
		    HttpServletResponse httpResponse = (HttpServletResponse) response;
		    //authorzation : "Bearer <token>"
		    String header = httpRequest.getHeader("authorization");
		    if(header == null || !header.startsWith("Bearer"))
		    	throw new ServletException("Missing or Invalid Bearer Token");
		    String jwtToken = header.substring(7);
		    String userId = Jwts.parser().setSigningKey("secretkey")
		    			 .parseClaimsJws(jwtToken).getBody().getSubject();
		    httpRequest.setAttribute("userId", userId);
		    chain.doFilter(request, response);
		   } catch(JwtException e) {
			   throw new ServletException("Token Expired. Please login");
		   } 
		  
	}
}
