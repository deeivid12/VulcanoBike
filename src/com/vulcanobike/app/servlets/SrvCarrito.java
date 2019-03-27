package com.vulcanobike.app.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.vulcanobike.app.business.Controlador;
import com.vulcanobike.app.entities.ItemPedido;
import com.vulcanobike.app.entities.Pedido;
import com.vulcanobike.app.entities.Producto;
import com.vulcanobike.app.entities.Usuario;
import com.vulcanobike.app.util.Emailer;

/**
 * Servlet implementation class SrvCarrito
 */
@WebServlet("/SrvCarrito")
public class SrvCarrito extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	Controlador ctrl = new Controlador();
	List<ItemPedido> items = new ArrayList<ItemPedido>();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SrvCarrito() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Usuario uActual = (Usuario)request.getSession().getAttribute("userSession");
		
		if(uActual != null) {
			
			String accion = request.getParameter("accion");
			
			//INFO PRODUCTO
			if(accion.equals("info")) {
				
				int id = Integer.parseInt(request.getParameter("id"));
				Producto p = ctrl.getOneProducto(id);
				if(p.getStock() > 0) {
					
					request.setAttribute("pEncontrado", p);
					RequestDispatcher view = getServletContext().getRequestDispatcher("/verProducto.jsp");
					view.forward(request, response);
					
				} else {
					
					request.setAttribute("error", "Sin stock disponible.");
					RequestDispatcher view = getServletContext().getRequestDispatcher("/SrvListarProducto");
					view.forward(request, response);
					
				}
				
			}
			
			//ADD CARRITO
			if(accion.equals("add")) {
						
				int id = Integer.parseInt(request.getParameter("id"));
				int cant = Integer.parseInt(request.getParameter("cant"));
				boolean itemRepetido = false;
				
				//armo item para guardar
				Producto p = ctrl.getOneProducto(id);
				ItemPedido itemP = new ItemPedido();
				itemP.setProducto(p);
				itemP.setCantidad(cant);
				itemP.setImporte(p.getPrecio()*cant);
				

					
				//verifico si hay repeticion de items
				for(ItemPedido ip : items) {
						if(ip.getProducto().getId() == itemP.getProducto().getId()) {
							ip.setCantidad(ip.getCantidad()+itemP.getCantidad());
							itemRepetido = true;
					}
				}
				if(!itemRepetido) items.add(itemP);

				
				//actualizar stock actual de producto
				if((p.getStock()-cant)<=0) p.setStock(0);
				else p.setStock(p.getStock()-cant);
				ctrl.updateProducto(p);
				
			
				request.setAttribute("items", items);
				RequestDispatcher view = getServletContext().getRequestDispatcher("/SrvListarProducto");
				view.forward(request, response);
				
			}
			
			
			if(accion.equals("ver")) {
				
					
				if(items != null) {
						
					//calculo importe total para mostrar en carrito
					float importeTotal = 0;
					importeTotal = ctrl.calcularImportePedido(items);
						
					request.setAttribute("importe", importeTotal);
					request.setAttribute("items", items);
						
					}
				
				RequestDispatcher view = getServletContext().getRequestDispatcher("/carrito.jsp");
				view.forward(request, response);
				
			}
			
			
			if(accion.equals("fin")) {
				
				if(!items.isEmpty()) { //valido que pedido tenga items
					
					Pedido pedido = new Pedido();
					pedido.setUsuario(uActual);
					pedido.setItems(items);
					pedido.setImporte(ctrl.calcularImportePedido(items)); 
					ctrl.addPedido(pedido);				
					
					//Emailer.getInstance().send(pedido.getUsuario().getEmail(), "Compra Exitosa", ctrl.generadorMensaje(pedido));
					

					items.clear();
					RequestDispatcher view = getServletContext().getRequestDispatcher("/finPedido.jsp");
					view.forward(request, response);
					
				}
				else {
					request.setAttribute("error", "Carrito vacio. Por favor, agregue uno o mas productos a su compra.");
					request.getRequestDispatcher("error.jsp").forward(request, response);
				}
				
				
			}
			
			if(accion.equals("eliminar")) {
				
				int id = Integer.parseInt(request.getParameter("id"));
				int indice = 0;
				
				//consulto indice de producto a eliminar de carrito y devuelvo cantidad a stock!
				for(ItemPedido ip : items) {
					if(ip.getProducto().getId() == id) {
						
						//actualizar stock actual de producto
						ip.getProducto().setStock(ip.getProducto().getStock()+ip.getCantidad());
						ctrl.updateProducto(ip.getProducto());
						break;
					}
					else indice=indice+1;
				}
				items.remove(indice);//elimino producto de carrito
				request.setAttribute("importe", ctrl.calcularImportePedido(items));
				request.setAttribute("items", items);
				
				
				
				
				RequestDispatcher view = getServletContext().getRequestDispatcher("/carrito.jsp");
				view.forward(request, response);
			}
			
		} else {
			RequestDispatcher view = getServletContext().getRequestDispatcher("/login.jsp");
			view.forward(request, response);
		}
		
		
			
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	

}
