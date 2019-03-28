package com.vulcanobike.app.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vulcanobike.app.business.Controlador;
import com.vulcanobike.app.entities.Usuario;
import com.vulcanobike.app.entities.Usuario.TiposUsuario;

/**
 * Servlet implementation class SrvUsuario
 */
@WebServlet("/SrvUsuario")
public class SrvUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SrvUsuario() {
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
		// TODO Auto-generated method stub
		//doGet(request, response);
		Usuario usuario = new Usuario();
		Controlador ctrl = new Controlador();
		String accion = request.getParameter("accion");
		
		//LOGIN
		if(accion.equals("login")) {
			String user = request.getParameter("usuario");
			String pass = request.getParameter("password");
			
			usuario = ctrl.login(user, pass);
			request.getSession().setAttribute("userSession", usuario); //envio usuario a sesion
			if(usuario!=null) {
				if(usuario.getTipoUsuario().equals(TiposUsuario.Administrador)) {
					request.getRequestDispatcher("").forward(request, response);
				}
				if(usuario.getTipoUsuario().equals(TiposUsuario.Usuario)) {
					request.getRequestDispatcher("SrvListarProductoCarrito").forward(request, response);
				}
				
			}
			else {
				System.out.println("ERRORRRRRR");
				request.setAttribute("messageError", "Usuario y/o Contraseña incorrecto");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
			
		}
		
		//LOGOFF
		if(accion.equals("logoff")) {
			request.getSession().invalidate();
			request.getRequestDispatcher("login.jsp").forward(request, response);
			
		}
		
		//REGISTRO
		if(accion.equals("registro")) {
			String user = request.getParameter("usuario");
			String pass = request.getParameter("password");
			String nombre = request.getParameter("nombre");
			String apellido = request.getParameter("apellido");
			String email = request.getParameter("email");
			String direccion = request.getParameter("direccion");
			String telefono = request.getParameter("telefono");
			
			boolean repetido = false;
			Usuario u = ctrl.getOneUsuarioByEmail(email);
			if(u!=null) repetido=true;
			u = ctrl.getOneUsuarioByUser(user);
			if(u!=null) repetido=true;
			
			if(!repetido) { //valido que user y email no esten registrado previamente
				usuario.setNombre(nombre);
				usuario.setApellido(apellido);
				usuario.setEmail(email);
				usuario.setDireccion(direccion);
				usuario.setTelefono(telefono);
				usuario.setUser(user);
				usuario.setPassword(pass);
				usuario.setTipoUsuario(TiposUsuario.Usuario);//en este registor siempre se van a rgistrar tipo usuario.
				ctrl.registro(usuario);
				request.getRequestDispatcher("login.jsp").forward(request, response);
			} else {
				System.out.println("User o email ya registrado. Intentar con otro.");
				request.getRequestDispatcher("registro.jsp").forward(request, response);
			}
			
		}
			
	}
		
	

}
