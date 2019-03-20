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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String id = request.getParameter("id"); //despues cambiar nombre por id y hacer conversion a integer!!
		String accion = request.getParameter("accion");
		
		//ABM EDITAR
		if(accion.equals("editar")) {
					
			TipoProducto tp = ctrl.findOneTipoProducto(id);
			request.setAttribute("tpEncontrado", tp);
			RequestDispatcher view = getServletContext().getRequestDispatcher("/editarTipoProducto.jsp");
			view.forward(request, response);
			
		}
		
		//ABM ELIMINAR
		if(accion.equals("eliminar")) {
			ctrl.deleteTipoProducto(id);
			response.sendRedirect("srvListarTipoProducto");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();//para mostrar mensaje
		String accion = request.getParameter("accion");
		
		//ABM EDITAR
		if(accion.equals("editar")) {
			
			TipoProducto tProducto = new TipoProducto();
			tProducto.setNombre(request.getParameter("nombre"));
			tProducto.setDescripcion(request.getParameter("descripcion"));
			ctrl.updateTipoProducto(tProducto);
			response.sendRedirect("srvListarTipoProducto");
			
		}
		
		//ABM ALTA
		if(accion.equals("alta")) {
			
			//GUARDAR TIPO PRODUCTO
			
			TipoProducto tProducto = new TipoProducto();
			tProducto.setNombre(request.getParameter("nombre"));
			tProducto.setDescripcion(request.getParameter("descripcion"));
			ctrl.addTipoProducto(tProducto);
			response.sendRedirect("srvListarTipoProducto"); //MANDO DIRECTAMENTE AL SERVLET QUE RESUELVE EL LISTADO
			
		}
		
		
		
		
		
		
	}

}
