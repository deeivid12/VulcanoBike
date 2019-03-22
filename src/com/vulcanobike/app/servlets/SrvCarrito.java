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
import com.vulcanobike.app.entities.TipoProducto;

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
		
		String accion = request.getParameter("accion");
		
		//INFO PRODUCTO
		if(accion.equals("info")) {
			
			int id = Integer.parseInt(request.getParameter("id"));
			Producto p = ctrl.GetOneProducto(id);
			request.setAttribute("pEncontrado", p);
			RequestDispatcher view = getServletContext().getRequestDispatcher("/verProducto.jsp");
			view.forward(request, response);
		}
		
		//ADD CARRITO
		if(accion.equals("add")) {
					
			HttpSession sesion= request.getSession(true);
			int id = Integer.parseInt(request.getParameter("id"));
			int cant = Integer.parseInt(request.getParameter("cant"));
			boolean itemRepetido = false;
			
			//armo item para guardar
			Producto p = ctrl.GetOneProducto(id);
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
			ctrl.UpdateProducto(p);
			
			//envio items a sesion
			sesion.setAttribute("items", items);
			

			RequestDispatcher view = getServletContext().getRequestDispatcher("/SrvListarProducto");
			view.forward(request, response);
			
		}
		
		
		if(accion.equals("ver")) {
			
			HttpSession sesion= request.getSession(true);
			if(sesion != null) {
				
				if(items != null) {
					
					//calculo importe total para mostrar en carrito
					float importeTotal = 0;
					for(ItemPedido ip : items) { //calculo importe de pedido!
						importeTotal = importeTotal + ip.getImporte();
					}
					
					request.setAttribute("importe", importeTotal);
					request.setAttribute("items", items);
					
				}
				
			}
			
			RequestDispatcher view = getServletContext().getRequestDispatcher("/carrito.jsp");
			view.forward(request, response);
			
		}
		
		
		if(accion.equals("fin")) {
			
			HttpSession sesion= request.getSession(false);
			Pedido pedido = new Pedido();
			float importePedido = 0;
			pedido.setItems(items);
			for(ItemPedido ip : pedido.getItems()) { //calculo importe de pedido!
				importePedido = importePedido + ip.getImporte();
			}
			pedido.setImporte(importePedido); 
			int id = ctrl.AddPedido(pedido);//se guarda el id del pedido			
			ctrl.AddItemPedido(items, id);//guardo itemsPedido en pedido generado
			
			//sesion.setAttribute("items", null);;//ELIMINO ITEMS DE SESION
			items.clear();
			RequestDispatcher view = getServletContext().getRequestDispatcher("/finPedido.jsp");
			view.forward(request, response);
			
		}
		
		if(accion.equals("eliminar")) {
			
			int id = Integer.parseInt(request.getParameter("id"));
			int indice = 0;
			
			//consulto indice de producto a eliminar de carrito y devuelvo cantidad a stock!
			for(ItemPedido ip : items) {
				if(ip.getProducto().getId() == id) {
					
					//actualizar stock actual de producto
					ip.getProducto().setStock(ip.getProducto().getStock()+ip.getCantidad());
					ctrl.UpdateProducto(ip.getProducto());
					break;
				}
				else indice=indice+1;
			}
			items.remove(indice);//elimino producto de carrito
			
			
			
			
			RequestDispatcher view = getServletContext().getRequestDispatcher("/carrito.jsp");
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
