package com.vulcanobike.app.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.vulcanobike.app.business.Controlador;
import com.vulcanobike.app.entities.ItemPedido;
import com.vulcanobike.app.entities.Producto;


/**
 * Servlet implementation class SrvListarProducto
 */
@WebServlet("/SrvListarProducto")
public class SrvListarProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	Controlador ctrl = new Controlador();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SrvListarProducto() {
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
		
		List<Producto> catalogoProducto = ctrl.getAllProducto();
		List<ItemPedido> items = new ArrayList<ItemPedido>();
		
		HttpSession sesion= (HttpSession) request.getSession();
		
		if(sesion != null) {
			items = (List<ItemPedido>) sesion.getAttribute("items");
			if(items != null) {
				for(ItemPedido ip : items) {
					System.out.println("ITEMSSSSS: " + ip.getProducto().getNombre());
			}
			
			}
		}
		
		
		request.setAttribute("catProducto", catalogoProducto);
		//request.getRequestDispatcher("listarProductos.jsp").forward(request, response);
		request.getRequestDispatcher("homeCarrito.jsp").forward(request, response);
	}
		

}
