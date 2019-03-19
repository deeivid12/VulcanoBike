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
 * Servlet implementation class srvTipoProducto
 */
@WebServlet("/srvMarca")
public class SrvMarca extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static Controlador ctrl = new Controlador();
	
	
       
    public static Controlador getCtrl() {
		return ctrl;
	}

	public static void setCtrl(Controlador ctrl) {
		SrvMarca.ctrl = ctrl;
	}

	/**
     * @see HttpServlet#HttpServlet()
     */
    public SrvMarca() {
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
		
		String accion = request.getParameter("accion");//Obtengo accion correspondiente a peticion
		if(accion.equals("guardar")) {
			
			Marca marca = new Marca();
			marca.setNombre(request.getParameter("nombre"));
			marca.setDescripcion(request.getParameter("descripcion"));
			marca.setOrigen(request.getParameter("origen"));
			ctrl.addMarca(marca);
			accion = "listar"; //CAMBIO ACCION PARA QUE ACTO SEGUIDO LISTE TODAS LAS MARCAS!
			
		} if(accion.equals("listar")) {
			List<Marca> catalogoMarca = ctrl.getAllMarca();
			request.setAttribute("catMarca", catalogoMarca);
			request.getRequestDispatcher("listarMarca.jsp").forward(request, response);
		}
		
		
	}

}
