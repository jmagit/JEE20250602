package com.example.presentation.web;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;

@WebFilter(filterName = "SaludoFilter", urlPatterns = { "/saluda/*" })
public class SaludoFilter extends HttpFilter implements Filter {
	public void init(FilterConfig fConfig) throws ServletException { }

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		int hora = GregorianCalendar.getInstance().get(Calendar.HOUR_OF_DAY);
		if (8 <= hora && hora < 14)
			request.setAttribute("saludoMsg", "Buenos dias");
		else if (14 <= hora && hora < 21)
			request.setAttribute("saludoMsg", "Buenas tardes");
		else
			request.setAttribute("saludoMsg", "Buenas noches");
		chain.doFilter(request, response);
	}

	public void destroy() { }
}
