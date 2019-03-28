package com.vulcanobike.app.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vulcanobike.app.business.Controlador;
import com.vulcanobike.app.entities.Aplicacion;
import com.vulcanobike.app.entities.Marca;
import com.vulcanobike.app.entities.Producto;
import com.vulcanobike.app.entities.Rodado;
import com.vulcanobike.app.entities.TipoProducto;

/**
 * Servlet implementation class SrvProducto
 */
@WebServlet("/SrvProducto")
public class SrvProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Controlador ctrl = new Controlador();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SrvProducto() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String accion = request.getParameter("accion");
		
		
		//ABM EDITAR
		if(accion.equals("editar")) {
					
			try {
				int id = Integer.parseInt(request.getParameter("id"));
				Producto p = ctrl.getOneProducto(id);
				request.setAttribute("pEncontrado", p);
				RequestDispatcher view = getServletContext().getRequestDispatcher("/editarProducto.jsp");
				view.forward(request, response);
			} catch (Exception e) {
				//e.printStackTrace();
				response.setStatus(404);
				request.setAttribute("error", e.getMessage());					
				request.getRequestDispatcher("error.jsp").forward(request, response);
			} 
			
		}
		
		//ABM ELIMINAR
		if(accion.equals("eliminar")) {
			
			try {
				int id = Integer.parseInt(request.getParameter("id"));
				ctrl.deleteProducto(id);
				response.sendRedirect("SrvListarProducto");

			} catch (Exception e) {
				
				//e.printStackTrace();
				response.setStatus(404);
				request.setAttribute("error", e.getMessage());					
				request.getRequestDispatcher("error.jsp").forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String accion = request.getParameter("accion");

		
		//ABM EDITAR
		if(accion.equals("editar")) {
			
			try {
				Producto producto = new Producto();
				TipoProducto tp = new TipoProducto();
				Aplicacion a = new Aplicacion();
				Marca m = new Marca();
				Rodado r = new Rodado();
				
				Integer id = Integer.parseInt(request.getParameter("id"));
				producto.setId(id);
				producto.setNombre(request.getParameter("nombre"));
				producto.setDescripcion(request.getParameter("descripcion"));
				producto.setPrecio(Float.parseFloat(request.getParameter("precio")));
				producto.setStock(Integer.parseInt(request.getParameter("stock")));
				producto.setImagen(request.getParameter("imagen"));
				tp = ctrl.findOneTipoProducto(Integer.parseInt(request.getParameter("tipoProducto")));
				a = ctrl.getOneAplicacion(Integer.parseInt(request.getParameter("aplicacion")));
				m = ctrl.getOneMarca(Integer.parseInt(request.getParameter("marca")));
				r = ctrl.getOneRodado(Integer.parseInt(request.getParameter("rodado")));
				producto.setTipoProducto(tp);
				producto.setAplicacionBicicleta(a);
				producto.setMarca(m);
				producto.setRodado(r);
				
				ctrl.updateProducto(producto);
				response.sendRedirect("SrvListarProducto");
			} catch (Exception e) {
				response.setStatus(404);
				request.setAttribute("error", e.getMessage());					
				request.getRequestDispatcher("error.jsp").forward(request, response);
			}
		}
		
		
		//ABM ALTA
		if(accion.equals("alta")) {
			
			//GUARDAR APLICACION
			
			TipoProducto tp = new TipoProducto();
			Aplicacion a = new Aplicacion();
			Marca m = new Marca();
			Rodado r = new Rodado();
			
			try {
				Producto producto = new Producto();
				producto.setNombre(request.getParameter("nombre"));
				producto.setDescripcion(request.getParameter("descripcion"));
				producto.setPrecio(Float.parseFloat(request.getParameter("precio")));
				producto.setStock(Integer.parseInt(request.getParameter("stock")));
				producto.setImagen(request.getParameter("imagen"));
				tp = ctrl.findOneTipoProducto(Integer.parseInt(request.getParameter("tipoProducto")));
				a = ctrl.getOneAplicacion(Integer.parseInt(request.getParameter("aplicacion")));
				m = ctrl.getOneMarca(Integer.parseInt(request.getParameter("marca")));
				r = ctrl.getOneRodado(Integer.parseInt(request.getParameter("rodado")));
				producto.setTipoProducto(tp);
				producto.setAplicacionBicicleta(a);
				producto.setMarca(m);
				producto.setRodado(r);
				ctrl.addProducto(producto);
				response.sendRedirect("SrvListarProducto"); //MANDO DIRECTAMENTE AL SERVLET QUE RESUELVE EL LISTADO	
			} catch (Exception e) {
				response.setStatus(404);
				request.setAttribute("error", e.getMessage());					
				request.getRequestDispatcher("error.jsp").forward(request, response);
			}
		}

	}


}
