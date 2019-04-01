package com.vulcanobike.app.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vulcanobike.app.business.Controlador;
import com.vulcanobike.app.entities.Marca;
import com.vulcanobike.app.entities.Usuario;
import com.vulcanobike.app.entities.Usuario.TiposUsuario;

/**
 * Servlet implementation class SrvMarca
 */
@WebServlet("/SrvMarca")
public class SrvMarca extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Controlador ctrl = new Controlador();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SrvMarca() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String accion = request.getParameter("accion");
		Usuario usuario = (Usuario) request.getSession().getAttribute("userSession");

		if (usuario != null) {
			if (usuario.getTipoUsuario().equals(TiposUsuario.Administrador)) { // valido que solo puedan acceder
																				// administradores!

				// ABM EDITAR
				if (accion.equals("editar")) {

					try {
						int id = Integer.parseInt(request.getParameter("id"));
						Marca m = ctrl.getOneMarca(id);
						request.setAttribute("mEncontrado", m);
						RequestDispatcher view = getServletContext().getRequestDispatcher("/editarMarca.jsp");
						view.forward(request, response);
					} catch (Exception e) {
						// e.printStackTrace();
						response.setStatus(404);
						request.setAttribute("error", e.getMessage());
						request.getRequestDispatcher("error.jsp").forward(request, response);
					}

				}

				// ABM ELIMINAR
				if (accion.equals("eliminar")) {

					try {
						int id = Integer.parseInt(request.getParameter("id"));
						ctrl.deleteMarca(id);
						response.sendRedirect("SrvListarMarca");

					} catch (Exception e) {

						// e.printStackTrace();
						response.setStatus(404);
						request.setAttribute("error", e.getMessage());
						request.getRequestDispatcher("error.jsp").forward(request, response);
					}
				}
			} else { // en caso de no ser usuario administrador
				String error = "No tiene permisos suficientes para ver esta pagina.";
				response.setStatus(403);
				request.setAttribute("error", error);
				request.getRequestDispatcher("error.jsp").forward(request, response);
			}
		}
		else { // en caso de no estar logueado
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String accion = request.getParameter("accion");
		Usuario usuario = (Usuario) request.getSession().getAttribute("userSession");

		if (usuario != null) {
			if (usuario.getTipoUsuario().equals(TiposUsuario.Administrador)) { // valido que solo puedan acceder
																				// administradores!

				// ABM EDITAR
				if (accion.equals("editar")) {

					try {
						Marca marca = new Marca();
						Integer id = Integer.parseInt(request.getParameter("id"));
						marca.setId(id);
						marca.setNombre(request.getParameter("nombre"));
						marca.setDescripcion(request.getParameter("descripcion"));
						marca.setOrigen(request.getParameter("origen"));
						ctrl.updateMarca(marca);
						response.sendRedirect("SrvListarMarca");
					} catch (Exception e) {
						response.setStatus(404);
						request.setAttribute("error", e.getMessage());
						request.getRequestDispatcher("error.jsp").forward(request, response);
					}
				}

				// ABM ALTA
				if (accion.equals("alta")) {

					// GUARDAR APLICACION

					try {
						Marca marca = new Marca();
						marca.setNombre(request.getParameter("nombre"));
						marca.setDescripcion(request.getParameter("descripcion"));
						marca.setOrigen(request.getParameter("origen"));
						ctrl.addMarca(marca);
						response.sendRedirect("SrvListarMarca"); // MANDO DIRECTAMENTE AL SERVLET QUE RESUELVE EL
																	// LISTADO
					} catch (Exception e) {
						response.setStatus(404);
						request.setAttribute("error", e.getMessage());
						request.getRequestDispatcher("error.jsp").forward(request, response);
					}
				}
			} else { // en caso de no ser usuario administrador
				String error = "No tiene permisos suficientes para ver esta pagina.";
				response.setStatus(403);
				request.setAttribute("error", error);
				request.getRequestDispatcher("error.jsp").forward(request, response);
			}
		}else { // en caso de no estar logueado
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}

	}

}
