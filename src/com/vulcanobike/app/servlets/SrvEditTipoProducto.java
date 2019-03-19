package com.vulcanobike.app.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vulcanobike.app.business.Controlador;
import com.vulcanobike.app.entities.TipoProducto;

/**
 * Servlet implementation class SrvSaveTipoProducto
 */
@WebServlet("/SrvEditTipoProducto")
public class SrvEditTipoProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	Controlador ctrl = srvFormTipoProducto.getCtrl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SrvEditTipoProducto() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String id = request.getParameter("id"); //despues cambiar nombre por id y hacer conversion a integer!!
		System.out.println("NOMBRE PASADO POR GET!!: " + id);
		
		TipoProducto tp = ctrl.findOneById(id);
		System.out.println("TIPOPRODUCTO ENCONTRADO!!: " + tp.getNombre());

		
		request.setAttribute("tpEncontrado", tp);
		RequestDispatcher view = getServletContext().getRequestDispatcher("/Editar.jsp");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
