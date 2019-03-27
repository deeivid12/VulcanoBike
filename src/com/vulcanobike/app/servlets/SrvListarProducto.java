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
import com.vulcanobike.app.entities.Aplicacion;
import com.vulcanobike.app.entities.ItemPedido;
import com.vulcanobike.app.entities.Producto;
import com.vulcanobike.app.entities.Rodado;
import com.vulcanobike.app.entities.TipoProducto;


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

		String filtro = (String)request.getParameter("filtro");
		
		if(filtro != null) {
			
			List<Producto> catalogoProducto = new ArrayList<Producto>();
			List<TipoProducto> catalogoTipoProducto = new ArrayList<TipoProducto>();
			List<Aplicacion> catalogoAplicacion = new ArrayList<Aplicacion>();
			List<Rodado> catalogoRodado = new ArrayList<Rodado>();
			List<Producto> productosFiltrados = new ArrayList<Producto>();
			
			
			catalogoProducto = ctrl.getAllProducto();
			try {
				catalogoTipoProducto = ctrl.getAllTipoProducto();
				catalogoAplicacion = ctrl.getAllAplicacion();
				catalogoRodado = ctrl.getAllRodado();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			for(Producto p:catalogoProducto) {
				if(p.getTipoProducto().getNombre().equals(filtro)) productosFiltrados.add(p);
				if(p.getAplicacionBicicleta().getNombre().equals(filtro)) productosFiltrados.add(p);
				if(p.getRodado().getNombre().equals(filtro)) productosFiltrados.add(p);
				//hacer lo mismo para el resto de caracteristicas
			}
			
			request.setAttribute("catProducto", productosFiltrados);
			request.setAttribute("catTipoProducto", catalogoTipoProducto);
			request.setAttribute("catAplicacion", catalogoAplicacion);
			request.setAttribute("catRodado", catalogoRodado);
			request.getRequestDispatcher("homeCarrito.jsp").forward(request, response);
			
		}else {
			doPost(request, response);
		}
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		List<Producto> catalogoProducto = new ArrayList<Producto>();
		List<TipoProducto> catalogoTipoProducto = new ArrayList<TipoProducto>();
		List<Aplicacion> catalogoAplicacion = new ArrayList<Aplicacion>();
		List<Rodado> catalogoRodado = new ArrayList<Rodado>();
		catalogoProducto = ctrl.getAllProducto();
		try {
			catalogoTipoProducto = ctrl.getAllTipoProducto();
			catalogoAplicacion = ctrl.getAllAplicacion();
			catalogoRodado = ctrl.getAllRodado();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("catProducto", catalogoProducto);
		request.setAttribute("catTipoProducto", catalogoTipoProducto);
		request.setAttribute("catAplicacion", catalogoAplicacion);
		request.setAttribute("catRodado", catalogoRodado);
		request.getRequestDispatcher("homeCarrito.jsp").forward(request, response);
		
	}
		

}
