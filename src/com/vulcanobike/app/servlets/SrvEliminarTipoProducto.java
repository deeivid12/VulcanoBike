package com.vulcanobike.app.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vulcanobike.app.business.Controlador;

/**
 * Servlet implementation class Eliminar
 */
@WebServlet("/SrvEliminarTipoProducto")
public class SrvEliminarTipoProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;
		Controlador ctrl = srvFormTipoProducto.getCtrl();
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SrvEliminarTipoProducto() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//.getWriter().append("Served at: ").append(request.getContextPath());
		String id=request.getParameter("id");
		ctrl.deleteTipoProducto(id);
		response.sendRedirect("srvListarTipoProducto");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
