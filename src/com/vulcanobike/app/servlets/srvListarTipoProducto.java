package com.vulcanobike.app.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vulcanobike.app.business.Controlador;
import com.vulcanobike.app.entities.TipoProducto;

/**
 * Servlet implementation class srvListadoTipoProducto
 */
@WebServlet("/srvListarTipoProducto")
public class srvListarTipoProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Controlador ctrl = SrvTipoProducto.getCtrl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public srvListarTipoProducto() {
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
		
		TipoProducto tpActual = new TipoProducto();
		List<TipoProducto> catalogoTipoProducto = ctrl.getAllTipoProducto();
		for(TipoProducto tp : catalogoTipoProducto) {
			tpActual.setNombre(tp.getNombre());
			tpActual.setDescripcion(tp.getDescripcion());
		}
		
		
		request.setAttribute("nombre", tpActual.getNombre());
		request.setAttribute("descripcion", tpActual.getDescripcion());
		request.setAttribute("catTipoProducto", catalogoTipoProducto);
		request.getRequestDispatcher("listarTipoProducto.jsp").forward(request, response);
	}

}
