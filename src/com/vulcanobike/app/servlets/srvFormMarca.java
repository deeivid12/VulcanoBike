package com.vulcanobike.app.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vulcanobike.app.business.Controlador;
import com.vulcanobike.app.entities.Marca;

/**
 * Servlet implementation class srvTipoProducto
 */
@WebServlet("/srvFormMarca")
public class srvFormMarca extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static Controlador ctrl = new Controlador();
	
	
       
    public static Controlador getCtrl() {
		return ctrl;
	}

	public static void setCtrl(Controlador ctrl) {
		srvFormMarca.ctrl = ctrl;
	}

	/**
     * @see HttpServlet#HttpServlet()
     */
    public srvFormMarca() {
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
		
		Marca marca = new Marca();
		marca.setNombre(request.getParameter("nombre"));
		marca.setDescripcion(request.getParameter("descripcion"));
		marca.setOrigen(request.getParameter("origen"));
		
		System.out.println("ORIGEENNNNN: " + marca.getOrigen());
		
		ctrl.addMarca(marca);
		response.sendRedirect("srvListarMarca"); //MANDO DIRECTAMENTE AL SERVLET QUE RESUELVE EL LISTADO
		
	}

}
