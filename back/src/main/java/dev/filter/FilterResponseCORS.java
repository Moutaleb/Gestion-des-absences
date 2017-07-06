package dev.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class FilterResponseCORS extends OncePerRequestFilter {

	@Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain)
        throws ServletException, IOException {
        
        httpServletResponse.addHeader("Access-Control-Allow-Origin", "*");
        
        httpServletResponse.addHeader("Access-Control-Allow-Methods","GET, POST, PUT, DELETE, OPTIONS");

        httpServletResponse.addHeader("Access-Control-Allow-Headers","origin, content-type, accept, X-Requested-With, If-Modified-Since, X-Requested-By");

        filterChain.doFilter(httpServletRequest, httpServletResponse);
        
    }

}