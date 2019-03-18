package com.vulcanobike.app.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vulcanobike.app.business.Controlador;
import com.vulcanobike.app.entities.Marca;

/**
 * Servlet implementation class srvListadoTipoProducto
 */
@WebServlet("/srvListarMarca")
public class srvListarMarca extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Controlador ctrl = srvFormMarca.getCtrl(); //ESTE DESPUES VA A SER UNO SOLO, QUE SE VA A INVOCAR EN LA PRIMER PANTALLA. LOGIN
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public srvListarMarca() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		List<Marca> catalogoMarca = ctrl.getAllMarca();
		request.setAttribute("catMarca", catalogoMarca);
		request.getRequestDispatcher("listarMarca.jsp").forward(request, response);
	}

}
