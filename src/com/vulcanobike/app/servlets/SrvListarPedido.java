package com.vulcanobike.app.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vulcanobike.app.business.Controlador;
import com.vulcanobike.app.entities.Pedido;
import com.vulcanobike.app.entities.Usuario;
import com.vulcanobike.app.entities.Usuario.TiposUsuario;

/**
 * Servlet implementation class srvListadoPedido
 */
@WebServlet("/SrvListarPedidoUsuario")
public class SrvListarPedido extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Controlador ctrl = new Controlador();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SrvListarPedido() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		Usuario usuario = (Usuario) request.getSession().getAttribute("userSession");

		if (usuario != null) {

			try {
				List<Pedido> catalogoPedido = ctrl.getAllPedidoByIdUsuario(usuario.getId());
				request.setAttribute("catPedido", catalogoPedido);
				request.getRequestDispatcher("listarPedidoUsuario.jsp").forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
				response.setStatus(404);
				request.setAttribute("error", e.getMessage());
				request.getRequestDispatcher("error.jsp").forward(request, response);

			}

		}

		else { // en caso de no estar logueado
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}

	}

}
