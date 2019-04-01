package com.vulcanobike.app.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vulcanobike.app.business.Controlador;
import com.vulcanobike.app.entities.Marca;
import com.vulcanobike.app.entities.Usuario;
import com.vulcanobike.app.entities.Usuario.TiposUsuario;

/**
 * Servlet implementation class SrvListarMarca
 */
@WebServlet("/SrvListarMarca")
public class SrvListarMarca extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Controlador ctrl = new Controlador();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SrvListarMarca() {
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
		
		Usuario usuario = (Usuario)request.getSession().getAttribute("userSession");
		
		if(usuario != null) {
			if(usuario.getTipoUsuario().equals(TiposUsuario.Administrador)) { //valido que solo puedan acceder administradores!
				
				try {
					List<Marca> catalogoMarca = ctrl.getAllMarca();
					request.setAttribute("catMarca", catalogoMarca);
					request.getRequestDispatcher("listarMarca.jsp").forward(request, response);
					} catch (Exception e) {
					
					response.setStatus(404);
					request.setAttribute("error", e.getMessage());					
					request.getRequestDispatcher("error.jsp").forward(request, response);
				}
				
			}
			
			else { //en caso de no ser usuario administrador
				String error = "No tiene permisos suficientes para ver esta pagina.";
				response.setStatus(403);
				request.setAttribute("error", error);
				request.getRequestDispatcher("error.jsp").forward(request, response); 
			}
		} 
		else { //en caso de no estar logueado
		
			request.getRequestDispatcher("login.jsp").forward(request, response);		
		}
		
	}

}
