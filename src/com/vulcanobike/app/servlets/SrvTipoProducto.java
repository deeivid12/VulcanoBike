package com.vulcanobike.app.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vulcanobike.app.business.Controlador;
import com.vulcanobike.app.entities.TipoProducto;
import com.vulcanobike.app.entities.Usuario;
import com.vulcanobike.app.entities.Usuario.TiposUsuario;

/**
 * Servlet implementation class SrvSaveTipoProducto
 */
@WebServlet("/SrvTipoProducto")
public class SrvTipoProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static Controlador ctrl = new Controlador();

	public static Controlador getCtrl() {
		return ctrl;
	}

	public static void setCtrl(Controlador ctrl) {
		SrvTipoProducto.ctrl = ctrl;
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SrvTipoProducto() {
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
						TipoProducto tp = ctrl.findOneTipoProducto(id);
						request.setAttribute("tpEncontrado", tp);
						RequestDispatcher view = getServletContext().getRequestDispatcher("/editarTipoProducto.jsp");
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
						ctrl.deleteTipoProducto(id);
						response.sendRedirect("srvListarTipoProducto");

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
						TipoProducto tProducto = new TipoProducto();
						Integer id = Integer.parseInt(request.getParameter("id"));
						tProducto.setId(id);
						tProducto.setNombre(request.getParameter("nombre"));
						tProducto.setDescripcion(request.getParameter("descripcion"));
						ctrl.updateTipoProducto(tProducto);
						response.sendRedirect("srvListarTipoProducto");
					} catch (Exception e) {
						response.setStatus(404);
						request.setAttribute("error", e.getMessage());
						request.getRequestDispatcher("error.jsp").forward(request, response);
					}
				}

				// ABM ALTA
				if (accion.equals("alta")) {

					// GUARDAR TIPO PRODUCTO

					try {
						TipoProducto tProducto = new TipoProducto();
						tProducto.setNombre(request.getParameter("nombre"));
						tProducto.setDescripcion(request.getParameter("descripcion"));
						ctrl.addTipoProducto(tProducto);
						response.sendRedirect("srvListarTipoProducto"); // MANDO DIRECTAMENTE AL SERVLET QUE RESUELVE EL
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
