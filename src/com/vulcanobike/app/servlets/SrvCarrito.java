package com.vulcanobike.app.servlets;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.vulcanobike.app.entities.Pedido.EstadosPedido;
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
	//List<ItemPedido> items = new ArrayList<ItemPedido>();
	
	
       
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
		List<ItemPedido> itemsSesion = (List<ItemPedido>)request.getSession().getAttribute("items");
		
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
					RequestDispatcher view = getServletContext().getRequestDispatcher("/SrvListarProductoCarrito");
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
				
				if(itemsSesion == null) {
					
					//actualizar stock actual de producto
					if((p.getStock()-cant)<=0) p.setStock(0);
					else p.setStock(p.getStock()-cant);
					ctrl.updateProducto(p);
					
					itemsSesion = new ArrayList<ItemPedido>();
					itemsSesion.add(itemP);
					request.getSession().setAttribute("items", itemsSesion);
					RequestDispatcher view = getServletContext().getRequestDispatcher("/SrvListarProductoCarrito");
					view.forward(request, response);
					
				} else {
					
					//verifico si hay repeticion de items
					for(ItemPedido ip : itemsSesion) {
							if(ip.getProducto().getId() == itemP.getProducto().getId()) {
								ip.setCantidad(ip.getCantidad()+itemP.getCantidad());
								itemRepetido = true;
						}
					}
					if(!itemRepetido) itemsSesion.add(itemP);

					
					//actualizar stock actual de producto
					if((p.getStock()-cant)<=0) p.setStock(0);
					else p.setStock(p.getStock()-cant);
					ctrl.updateProducto(p);
					
				
					RequestDispatcher view = getServletContext().getRequestDispatcher("/SrvListarProductoCarrito");
					view.forward(request, response);
					
					
				}
				
				
				

					
				
			}
			
			
			if(accion.equals("ver")) {
				

				if(itemsSesion != null)	{
						
					//calculo importe total para mostrar en carrito
					float importeTotal = 0;
					importeTotal = ctrl.calcularImportePedido(itemsSesion);
						
					request.setAttribute("importe", importeTotal);
					
					RequestDispatcher view = getServletContext().getRequestDispatcher("/carrito.jsp");
					view.forward(request, response);
						
					} else {
						request.setAttribute("error", "Carrito vacio. Por favor, agregue uno o mas productos a su compra.");
						RequestDispatcher view = getServletContext().getRequestDispatcher("/error.jsp");
						view.forward(request, response);
					}
				
			}
			
			
			if(accion.equals("fin")) {
				
				
				if(!itemsSesion.isEmpty()) { //valido que pedido tenga items
					
					Pedido pedido = new Pedido();
					Date fechaEmision = new Date();
					DateFormat formatoFecha = new SimpleDateFormat("yyyy/MM/dd");
					
					pedido.setUsuario(uActual);
					pedido.setItems(itemsSesion);
					pedido.setImporte(ctrl.calcularImportePedido(itemsSesion)); 
					pedido.setEstado(EstadosPedido.Pendiente);
					pedido.setFechaEmision(formatoFecha.format((fechaEmision)));
					ctrl.addPedido(pedido);				
					
					Emailer.getInstance().send(pedido.getUsuario().getEmail(), "Compra Exitosa", ctrl.generadorMensaje(pedido));
					

					itemsSesion.clear();
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
				for(ItemPedido ip : itemsSesion) {
					if(ip.getProducto().getId() == id) {
						
						//actualizar stock actual de producto
						ip.getProducto().setStock(ip.getProducto().getStock()+ip.getCantidad());
						ctrl.updateProducto(ip.getProducto());
						break;
					}
					else indice=indice+1;
				}
				itemsSesion.remove(indice);//elimino producto de carrito
				request.setAttribute("importe", ctrl.calcularImportePedido(itemsSesion));
				
				
				
				
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
