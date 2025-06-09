package com.example.presentation.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class SaludaServlet
 */
@WebServlet({ "/Saluda", "/saluda", "/saluda/*" })
public class SaludaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource(lookup = "SakilaDataSource")
	private DataSource ds;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SaludaServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html><html>");
		out.println("<head><title>Curso</title></head>");
		out.println("<body>");
		out.println("<h1>" + request.getAttribute("saludoMsg") + ", mundo</h1>");
		out.println("<div>" + request.getMethod() +"</div>");
		out.println("</body></html>");
	}

}
