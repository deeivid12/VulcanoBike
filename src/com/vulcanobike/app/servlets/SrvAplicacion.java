package com.vulcanobike.app.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vulcanobike.app.business.Controlador;
import com.vulcanobike.app.entities.Aplicacion;

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
		
		String accion = request.getParameter("accion");//Obtengo accion correspondiente a peticion
		if(accion.equals("guardar")) {
			
			Aplicacion aplicacion = new Aplicacion();
			aplicacion.setNombre(request.getParameter("nombre"));
			aplicacion.setDescripcion(request.getParameter("descripcion"));
			ctrl.addAplicacion(aplicacion);
			accion = "listar"; //CAMBIO ACCION PARA QUE ACTO SEGUIDO LISTE TODAS LAS MARCAS!
			
		} if(accion.equals("listar")) {
			List<Aplicacion> catalogoAplicacion = ctrl.getAllAplicacion();
			request.setAttribute("catAplicacion", catalogoAplicacion);
			request.getRequestDispatcher("listarAplicacion.jsp").forward(request, response);
		}
		
	}

}
