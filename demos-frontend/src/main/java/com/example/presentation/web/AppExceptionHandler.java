package com.example.presentation.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AppExceptionHandler")
public class AppExceptionHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processError(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processError(request, response);
	}

	private void processError(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		// Analyze the servlet exception
		Throwable throwable = (Throwable) request
				.getAttribute("javax.servlet.error.exception");
		Integer statusCode = (Integer) request
				.getAttribute("javax.servlet.error.status_code");
		String servletName = (String) request
				.getAttribute("javax.servlet.error.servlet_name");
		if (servletName == null) {
			servletName = "Unknown";
		}
		String requestUri = (String) request
				.getAttribute("javax.servlet.error.request_uri");
		if (requestUri == null) {
			requestUri = "Unknown";
		}
		request.setAttribute("status", statusCode != null ? statusCode : 500);
		request.setAttribute("error", statusCode == 404 ? "NOT FOUND" : "INTERNAL SERVER ERROR" );
		request.setAttribute("message", statusCode == 404 ? "Página no encontrada" : throwable.getMessage());
		request.setAttribute("timestamp", new Date());
		request.setAttribute("path", requestUri);
		request.getRequestDispatcher("/WEB-INF/errorPage.jsp").forward(request, response);
	}
}
