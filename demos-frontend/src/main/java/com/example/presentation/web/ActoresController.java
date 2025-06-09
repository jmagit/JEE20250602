package com.example.presentation.web;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import com.example.contracts.domain.services.ActoresService;
import com.example.core.domain.exceptions.NotFoundException;
import com.example.domain.entities.Actor;

/**
 * Servlet implementation class ActoresController
 */
@WebServlet("/actores/*")
public class ActoresController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ActoresService srv;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	@Inject
	public ActoresController(ActoresService srv) {
		super();
		this.srv = srv;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			if (request.getPathInfo() == null || request.getPathInfo().equals("/")) {
				request.setAttribute("listado", srv.getAll());
				request.getRequestDispatcher("/WEB-INF/views/actores/list.jsp").forward(request, response);
				return;
			}
			String[] path = request.getPathInfo().substring(1).split("/");
			if (path.length == 1) {
				if (path[0].toLowerCase().equals("add")) {
					request.setAttribute("modo", "add");
					sendForm("add", new Actor(), request, response);
					return;
				}
				try {
					request.setAttribute("elemento", srv.getOne(Integer.valueOf(path[0]))
							.orElseThrow(() -> new NotFoundException("Actor no encontrado")));
					request.setAttribute("modo", "view");
					request.getRequestDispatcher("/WEB-INF/views/actores/view.jsp").forward(request, response);
				} catch (NumberFormatException e) {
					response.sendError(HttpServletResponse.SC_BAD_REQUEST,
							request.getPathInfo() + " ID de actor requerido");
				}
				return;
			}
			if (path.length == 2) {
				try {
					int id = Integer.parseInt(path[0]);
					if (path[1].toLowerCase().equals("edit")) {
						sendForm("edit", srv.getOne(Integer.valueOf(path[0]))
								.orElseThrow(() -> new NotFoundException("Actor no encontrado")), request, response);
						return;
					}
					if (path[1].toLowerCase().equals("delete")) {
						request.setAttribute("elemento", srv.getOne(Integer.valueOf(path[0]))
								.orElseThrow(() -> new NotFoundException("Actor no encontrado")));
						request.setAttribute("modo", "delete");
						request.getRequestDispatcher("/WEB-INF/views/actores/view.jsp").forward(request, response);
						return;
					}
				} catch (NumberFormatException e) {
					response.sendError(HttpServletResponse.SC_BAD_REQUEST, "No se ha especificado un actor");
				}
				return;
			}

		} catch (NotFoundException e) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND, "No se ha especificado un actor");
		}
	}

	private void sendForm(String modo, Actor item, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("elemento", item);
		request.setAttribute("modo", modo);
		request.getRequestDispatcher("/WEB-INF/views/actores/form.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Transactional
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getPathInfo() == null || request.getPathInfo().equals("/")) {
			doGet(request, response);
			return;
		}
		String[] path = request.getPathInfo().substring(1).split("/");
		if (path.length > 2 || (!path[0].toLowerCase().equals("add") && !path[1].toLowerCase().equals("edit")
				&& !path[1].toLowerCase().equals("delete"))) {
			doGet(request, response);
			return;
		}
		String modo = path.length == 1 ? "add" : path[1].toLowerCase();
		int id = -1;
		if (path.length == 2)
			try {
				id = Integer.valueOf(path[0]);
			} catch (NumberFormatException e) {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID de actor requerido");
				return;
			}
		if ("delete".equals(modo)) {
			try {
				srv.deleteById(id);
				goBack(request, response);
			} catch (Exception e) {
				request.setAttribute("errores", e.getMessage());
				doGet(request, response);
			}
			return;
		}
		Actor item = new Actor(Integer.parseInt(request.getParameter("actorId")), request.getParameter("firstName"),
				request.getParameter("lastName"));
		try {
			if ("add".equals(modo)) {
				srv.add(item);
			} else {
				srv.modify(item);
			}
			goBack(request, response);
		} catch (Exception e) {
			request.setAttribute("errores", e.getClass().getCanonicalName() + " " + e.getMessage());
			sendForm(modo, item, request, response);
		}
	}

	private void goBack(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.sendRedirect(request.getContextPath() + "/actores");
	}
}
