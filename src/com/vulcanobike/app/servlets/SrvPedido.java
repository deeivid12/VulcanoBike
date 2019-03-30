package com.vulcanobike.app.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vulcanobike.app.business.Controlador;
import com.vulcanobike.app.entities.ItemPedido;
import com.vulcanobike.app.entities.Pedido;
import com.vulcanobike.app.entities.Pedido.EstadosPedido;

/**
 * Servlet implementation class SrvSavePedido
 */
@WebServlet("/SrvPedido")
public class SrvPedido extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static Controlador ctrl = new Controlador();
	
	
    
    public static Controlador getCtrl() {
		return ctrl;
	}

	public static void setCtrl(Controlador ctrl) {
		SrvPedido.ctrl = ctrl;
	}

	/**
     * @see HttpServlet#HttpServlet()
     */
    public SrvPedido() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String accion = request.getParameter("accion");
		
		
		//ABM MOSTRAR
		if(accion.equals("editar")) {
					
			try {
				int id = Integer.parseInt(request.getParameter("id"));
				Pedido p = ctrl.getOnePedido(id);
				request.setAttribute("pEncontrado", p);
				RequestDispatcher view = getServletContext().getRequestDispatcher("/editarPedido.jsp");
				view.forward(request, response);
				
			} catch (Exception e) {
				e.printStackTrace();
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
		
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();//para mostrar mensaje
		String accion = request.getParameter("accion");

		
		//ABM MOSTRAR
		if(accion.equals("editar")) {
			
			try {
				Pedido pedido = new Pedido();
				Integer id = Integer.parseInt(request.getParameter("id"));
				pedido.setId(id);
				switch(Integer.parseInt(request.getParameter("estado"))) {
				case 1: pedido.setEstado(EstadosPedido.Pendiente); break;
				case 2: pedido.setEstado(EstadosPedido.En_Proceso); break;
				case 3: pedido.setEstado(EstadosPedido.Enviado); break;
				}
				ctrl.updatePedido(pedido);
				response.sendRedirect("SrvListarPedido");
			} catch (Exception e) {
				response.setStatus(404);
				request.setAttribute("error", e.getMessage());					
				request.getRequestDispatcher("error.jsp").forward(request, response);
			}
		}
		
		
			
	}

}
