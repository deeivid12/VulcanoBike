package com.vulcanobike.app.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vulcanobike.app.business.Controlador;
import com.vulcanobike.app.entities.Aplicacion;
import com.vulcanobike.app.entities.TipoProducto;
import com.vulcanobike.app.entities.Usuario;
import com.vulcanobike.app.entities.Usuario.TiposUsuario;

/**
 * Servlet implementation class SrvAplicacionBicicleta
 */
@WebServlet("/SrvAplicacion")
public class SrvAplicacion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static Controlador ctrl = new Controlador();

	public static Controlador getCtrl() {
		return ctrl;
	}

	public static void setCtrl(Controlador ctrl) {
		SrvAplicacion.ctrl = ctrl;
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SrvAplicacion() {
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
						Aplicacion a = ctrl.getOneAplicacion(id);
						request.setAttribute("aEncontrado", a);
						RequestDispatcher view = getServletContext().getRequestDispatcher("/editarAplicacion.jsp");
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
						ctrl.deleteAplicacion(id);
						response.sendRedirect("SrvListarAplicacion");

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
		} else { // en caso de no estar logueado
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
						Aplicacion aplicacion = new Aplicacion();
						Integer id = Integer.parseInt(request.getParameter("id"));
						aplicacion.setId(id);
						aplicacion.setNombre(request.getParameter("nombre"));
						aplicacion.setDescripcion(request.getParameter("descripcion"));
						ctrl.updateAplicacion(aplicacion);
						response.sendRedirect("SrvListarAplicacion");
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
						Aplicacion aplicacion = new Aplicacion();
						aplicacion.setNombre(request.getParameter("nombre"));
						aplicacion.setDescripcion(request.getParameter("descripcion"));
						ctrl.addAplicacion(aplicacion);
						response.sendRedirect("SrvListarAplicacion"); // MANDO DIRECTAMENTE AL SERVLET QUE RESUELVE EL
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
		} else { // en caso de no estar logueado
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}

	}
}
