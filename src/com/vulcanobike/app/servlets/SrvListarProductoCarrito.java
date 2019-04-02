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
import com.vulcanobike.app.entities.Marca;
import com.vulcanobike.app.entities.Producto;
import com.vulcanobike.app.entities.Rodado;
import com.vulcanobike.app.entities.TipoProducto;


/**
 * Servlet implementation class SrvListarProducto
 */
@WebServlet("/SrvListarProductoCarrito")
public class SrvListarProductoCarrito extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	Controlador ctrl = new Controlador();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SrvListarProductoCarrito() {
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
			List<Marca> catalogoMarca = new ArrayList<Marca>();
			List<Producto> productosFiltrados = new ArrayList<Producto>();
			
			
			try {
				catalogoProducto = ctrl.getAllProducto();
				catalogoTipoProducto = ctrl.getAllTipoProducto();
				catalogoAplicacion = ctrl.getAllAplicacion();
				catalogoRodado = ctrl.getAllRodado();
				catalogoMarca = ctrl.getAllMarca();
				
				for(Producto p:catalogoProducto) {
					if((p.getTipoProducto().getNombre() != null) && (p.getTipoProducto().getNombre().equals(filtro))) productosFiltrados.add(p);
					if((p.getAplicacionBicicleta().getNombre() != null) && (p.getAplicacionBicicleta().getNombre().equals(filtro))) productosFiltrados.add(p);
					if((p.getRodado().getNombre() !=null) && (p.getRodado().getNombre().equals(filtro))) productosFiltrados.add(p);
					if((p.getMarca().getNombre() !=null) && (p.getMarca().getNombre().equals(filtro))) productosFiltrados.add(p);
					//hacer lo mismo para el resto de caracteristicas
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			request.setAttribute("catProducto", productosFiltrados);
			request.setAttribute("catTipoProducto", catalogoTipoProducto);
			request.setAttribute("catAplicacion", catalogoAplicacion);
			request.setAttribute("catRodado", catalogoRodado);
			request.setAttribute("catMarca", catalogoMarca);
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
		List<Marca> catalogoMarca = new ArrayList<Marca>();
		
		
		try {
			catalogoProducto = ctrl.getAllProducto();
			catalogoTipoProducto = ctrl.getAllTipoProducto();
			catalogoAplicacion = ctrl.getAllAplicacion();
			catalogoRodado = ctrl.getAllRodado();
			catalogoMarca = ctrl.getAllMarca();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("catProducto", catalogoProducto);
		request.setAttribute("catTipoProducto", catalogoTipoProducto);
		request.setAttribute("catAplicacion", catalogoAplicacion);
		request.setAttribute("catRodado", catalogoRodado);
		request.setAttribute("catMarca", catalogoMarca);
		request.getRequestDispatcher("homeCarrito.jsp").forward(request, response);
		
	}
		

}
