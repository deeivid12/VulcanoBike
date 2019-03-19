package com.vulcanobike.app.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vulcanobike.app.business.Controlador;
import com.vulcanobike.app.entities.TipoProducto;

/**
 * Servlet implementation class SrvEdit2TipoProducto
 */
@WebServlet("/SrvEdit2TipoProducto")
public class SrvEdit2TipoProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Controlador ctrl = srvFormTipoProducto.getCtrl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SrvEdit2TipoProducto() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		TipoProducto tProducto = new TipoProducto();
		tProducto.setNombre(request.getParameter("nombre"));
		tProducto.setDescripcion(request.getParameter("descripcion"));
		ctrl.updateTipoProducto(tProducto);
		response.sendRedirect("srvListarTipoProducto");
	}

}
