package com.example.presentation.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.example.contracts.distributed.services.ConverterBeanLocal;
import com.example.contracts.distributed.services.LikesBeanLocal;
import com.example.presentation.services.enterprise.CounterBean;

@WebServlet("/pruebas")
public class ConsoleTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		request.getRequestDispatcher("/WEB-INF/parts/header.jsp").include(request, response);

		cabeceras(request, response, out);
//		conexion(request, response, out);
//		ejb(request, response, out);

		request.getRequestDispatcher("/WEB-INF/parts/footer.jsp").include(request, response);
	}

	private void cabeceras(HttpServletRequest request, HttpServletResponse response, PrintWriter out) {
		out.println("<h2>cabeceras</h2>");
		out.println("<ul>");
		Enumeration<String> nombresDeCabeceras = request.getHeaderNames();
		while (nombresDeCabeceras.hasMoreElements()) {
			String cabecera = nombresDeCabeceras.nextElement();
			out.println("<li><b>" + cabecera + ": </b>" + request.getHeader(cabecera) + "</li>");
		}
		out.println("</ul>");
	}

	@Resource(lookup = "SakilaDataSource")
	private DataSource ds;

	private void conexion(HttpServletRequest request, HttpServletResponse response, PrintWriter out) {
		out.println("<h2>DataSource</h2>");
		out.println("<ul>");
		out.println("<li>" + ds.getClass().getCanonicalName() + "</li>");
		try (Connection con = ds.getConnection()) {
			out.println("<li>" + con.getClass().getCanonicalName() + "</li>");
			out.println("<li>" + con.getMetaData().getDatabaseProductName() + " v. "
					+ con.getMetaData().getDatabaseProductVersion() + "</li>");
			con.getClientInfo().forEach((k, v) -> out.println("<li><b>" + k + ": </b>" + v + "</li>"));
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		out.println("</ul>");
	}

	@EJB
	private ConverterBeanLocal converter;
	@EJB
//	@Inject @SessionScoped
	private LikesBeanLocal like;
//	@EJB
	@Inject @SessionScoped
	private LikesBeanPropio likePropio;
	@EJB
	private CounterBean counter;

	private void ejb(HttpServletRequest request, HttpServletResponse response, PrintWriter out) {
		out.println("<h2>EJB</h2>");
		out.println("<ul>");
		out.println("<li>Converter (stateless): " + converter.dollarToYen(BigDecimal.TEN) + " - "
				+ converter.dollarToYenLocal(BigDecimal.TEN) + "</li>");
		if (like == null) {
			out.println("<li>No Such EJB</li>");
		} else
			try {
				int actual = like.getHits();
				out.println("<li>Like (stateful): You has send " + actual + " like(s).[" + request.getSession().getId()
						+ "]</li>");
				if (actual > 10) {
					out.println("<li>Like remove</li>");
					like.remove();
				}
			} catch (Exception e) {
				out.println("<li>" + e.getClass().getCanonicalName() + ": " + e.getMessage() + "</li>");
			}
		if (likePropio == null) {
			out.println("<li>No Such EJB</li>");
		} else
			try {
				int actual = likePropio.getHits();
				out.println("<li>Like (stateful): You has send " + actual + " like(s).[" + request.getSession().getId()
						+ "]</li>");
				if (actual > 10) {
					out.println("<li>Like remove</li>");
					likePropio.remove();
				}
			} catch (Exception e) {
				out.println("<li>" + e.getClass().getCanonicalName() + ": " + e.getMessage() + "</li>");
			}
		try {
			int actual = counter.getHits();
			out.println("<li>Counter (singleton): This page has been accessed " + actual + " time(s).</li>");
			if (actual > 2) {
				out.println("<li>Counter remove</li>");
				counter.remove();
			}
		} catch (Exception e) {
			out.println("<li>" + e.getClass().getCanonicalName() + ": " + e.getMessage() + "</li>");
		}
		out.println("<li><a href='" + request.getRequestURI() + "'>Abrir el enlace en una ventana de incognito</a></li>");
		out.println("</ul>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		doGet(request, response);
	}

}
