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
		
		
		
		//ADD CARRITO
		if(accion.equals("add")) {
					
			HttpSession sesion= request.getSession(true);
			int id = Integer.parseInt(request.getParameter("id"));
			Producto p = ctrl.GetOneProducto(id);
			ItemPedido itemP = new ItemPedido();
			itemP.setProducto(p);
			itemP.setCantidad(1); //hardcodeado
			itemP.setImporte(150); //hardcodeado
			items.add(itemP);
			sesion.setAttribute("items", items);
			//request.setAttribute("tpEncontrado", tp);
			RequestDispatcher view = getServletContext().getRequestDispatcher("/SrvListarProducto");
			view.forward(request, response);
			
		}
		
		
		if(accion.equals("ver")) {
			
			HttpSession sesion= request.getSession(true);
			if(sesion != null) {
				
				if(items != null) {
					//items = (List<ItemPedido>) sesion.getAttribute("items");
					request.setAttribute("items", items);
					
				}
				
			}
			
			RequestDispatcher view = getServletContext().getRequestDispatcher("/carrito.jsp");
			view.forward(request, response);
			
		}
		
		
		if(accion.equals("fin")) {
			
			HttpSession sesion= request.getSession(false);
			Pedido pedido = new Pedido();
			pedido.setImporte(1000); //hardcodeado
			pedido.setItems(items);
			int id = ctrl.AddPedido(pedido);
			System.out.println("ID PEDIDO GUARDADO!!: " + id);
			
			for(ItemPedido ip : items) {
				System.out.println("ITEMS QUE SE VAN A GUARDAR!!!!: " + ip.getProducto().getNombre());
				ip.setIdPedido(id);
				ctrl.AddItemPedido(ip);
			}
			
			sesion.setAttribute("items", null);;//ELIMINO ITEMS DE SESION
			items.clear();
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
