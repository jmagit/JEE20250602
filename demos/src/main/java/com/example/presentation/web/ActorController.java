package com.example.presentation.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// /actores --> lista
// /actores/add --> crear
// /actores/1 --> detalle
// /actores/1/edit --> editar
// /actores/1/delete --> borrar
// /actores/1/peliculas --> detalle

/**
 * Servlet implementation class ActorController
 */
@WebServlet("/actores/*")
public class ActorController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ActorController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getPathInfo() == null || request.getPathInfo().equals("/")) {
			request.getRequestDispatcher("/WEB-INF/views/actores/list.jsp").forward(request, response);
			return;
		}
		String[] path = request.getPathInfo().substring(1).split("/");
		if (path.length == 1) {
			if (path[0].toLowerCase().equals("add")) {
				request.setAttribute("modo", "add");
				request.getRequestDispatcher("/WEB-INF/views/actores/form.jsp").forward(request, response);
				return;
			} else {
				try {
					int id = Integer.parseInt(path[0]);
					request.setAttribute("id", id);
					request.getRequestDispatcher("/WEB-INF/views/actores/view.jsp").forward(request, response);
					return;
				} catch (NumberFormatException e) {
					response.sendError(HttpServletResponse.SC_BAD_REQUEST, "No se ha especificado un actor");
				}
			}
		}
		if (path.length == 2 && path[1].toLowerCase().equals("edit")) {
			try {
				int id = Integer.parseInt(path[0]);
				request.setAttribute("modo", "edit");
				request.setAttribute("id", id);
				request.getRequestDispatcher("/WEB-INF/views/actores/form.jsp").forward(request, response);
				return;
			} catch (NumberFormatException e) {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST, "No se ha especificado un actor");
			}
		}
		// TODO Auto-generated method stub
		response.sendError(HttpServletResponse.SC_NOT_FOUND, "No se ha especificado un actor");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().println(request.getPathInfo());
		response.sendRedirect("./"); // Redirige a la lista de actores
		// doGet(request, response);
	}

}
