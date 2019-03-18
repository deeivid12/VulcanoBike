package com.vulcanobike.app.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vulcanobike.app.business.Controlador;
import com.vulcanobike.app.entities.TipoProducto;

/**
 * Servlet implementation class srvTipoProducto
 */
@WebServlet("/srvFormTipoProducto")
public class srvFormTipoProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static Controlador ctrl = new Controlador();
	
	
       
    public static Controlador getCtrl() {
		return ctrl;
	}

	public static void setCtrl(Controlador ctrl) {
		srvFormTipoProducto.ctrl = ctrl;
	}

	/**
     * @see HttpServlet#HttpServlet()
     */
    public srvFormTipoProducto() {
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
		
		TipoProducto tProducto = new TipoProducto();
		tProducto.setNombre(request.getParameter("nombre"));
		tProducto.setDescripcion(request.getParameter("descripcion"));
		ctrl.addTipoProducto(tProducto);
		
		System.out.println(tProducto.getNombre());
		System.out.println(tProducto.getDescripcion());
		
		//request.setAttribute("nombre", tProducto.getNombre());
		//request.setAttribute("descripcion", tProducto.getDescripcion());
		//request.getRequestDispatcher("listarTipoProducto.jsp").forward(request, response);
		response.sendRedirect("srvListarTipoProducto"); //MANDO DIRECTAMENTE AL SERVLET QUE RESUELVE EL LISTADO
		
	}

}
