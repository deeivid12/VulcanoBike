package com.vulcanobike.app.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vulcanobike.app.business.Controlador;
import com.vulcanobike.app.entities.Rodado;
import com.vulcanobike.app.entities.Usuario;
import com.vulcanobike.app.entities.Usuario.TiposUsuario;

/**
 * Servlet implementation class SrvUsuario
 */
@WebServlet("/SrvUsuario")
public class SrvUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Controlador ctrl = new Controlador();
	
       
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
		
		String accion = request.getParameter("accion");
		if(accion.equals("login")) doPost(request, response); //LOGIN POR POST
		if(accion.equals("logoff")) doPost(request, response); //LOGOFF POR POST
		if(accion.equals("registro")) doPost(request, response); //REGISTOR POR POST
	
		
		
		//ABM EDITAR
		if(accion.equals("editar")) {
					
			try {
				int id = Integer.parseInt(request.getParameter("id"));
				Usuario u = ctrl.getOneUsuario(id);
				request.setAttribute("uEncontrado", u);
				RequestDispatcher view = getServletContext().getRequestDispatcher("/editarUsuario.jsp");
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
				ctrl.deleteUsuario(id);
				response.sendRedirect("SrvListarUsuario");

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
		// TODO Auto-generated method stub
		//doGet(request, response);
		Usuario usuario = new Usuario();

		String accion = request.getParameter("accion");
		
		//LOGIN
		if(accion.equals("login")) {
			try {
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
					request.setAttribute("messageError", "Usuario y/o Contraseña incorrecto");
					request.getRequestDispatcher("login.jsp").forward(request, response);
				}
			} catch (Exception e) {
				
				response.setStatus(404);
				request.setAttribute("error", e.getMessage());					
				request.getRequestDispatcher("error.jsp").forward(request, response);
			}
			
		}
		
		//LOGOFF
		if(accion.equals("logoff")) {
			request.getSession().invalidate();
			request.getRequestDispatcher("login.jsp").forward(request, response);
			
		}
		
		//REGISTRO
		if(accion.equals("registro")) {
			
			
			try {
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
					request.setAttribute("messageError", "User o email ya registrado. Intentar con otro.");
					request.getRequestDispatcher("registro.jsp").forward(request, response);
				}
			} catch (Exception e) {
				
				response.setStatus(404);
				request.setAttribute("error", e.getMessage());					
				request.getRequestDispatcher("error.jsp").forward(request, response);
			}
			
		}
		
		
		// ABM EDITAR
		if (accion.equals("editar")) {

			try {
				usuario = new Usuario();
				Integer id = Integer.parseInt(request.getParameter("id"));
				String user = request.getParameter("usuario");
				String pass = request.getParameter("password");
				String nombre = request.getParameter("nombre");
				String apellido = request.getParameter("apellido");
				String email = request.getParameter("email");
				String direccion = request.getParameter("direccion");
				String telefono = request.getParameter("telefono");
				int tipoUsuario = Integer.parseInt(request.getParameter("tipoUsuario"));

				usuario.setId(id);
				usuario.setNombre(nombre);
				usuario.setApellido(apellido);
				usuario.setEmail(email);
				usuario.setDireccion(direccion);
				usuario.setTelefono(telefono);
				usuario.setUser(user);
				usuario.setPassword(pass);
				switch (tipoUsuario) {
				case 1:
					usuario.setTipoUsuario(TiposUsuario.Administrador);
					break;
				case 2:
					usuario.setTipoUsuario(TiposUsuario.Usuario);
					break;
				}
				ctrl.updateUsuario(usuario);
				response.sendRedirect("SrvListarUsuario");

			} catch (Exception e) {
				e.printStackTrace();
				response.setStatus(404);
				request.setAttribute("error", e.getMessage());
				request.getRequestDispatcher("error.jsp").forward(request, response);
			}
		}
		
		
		//ABM ALTA
			if(accion.equals("alta")) {
				
				try {
					usuario = new Usuario();
					
					String user = request.getParameter("usuario");
					String pass = request.getParameter("password");
					String nombre = request.getParameter("nombre");
					String apellido = request.getParameter("apellido");
					String email = request.getParameter("email");
					String direccion = request.getParameter("direccion");
					String telefono = request.getParameter("telefono");
					int tipoUsuario = Integer.parseInt(request.getParameter("tipoUsuario"));
					
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
						switch (tipoUsuario) {
						case 1:
							usuario.setTipoUsuario(TiposUsuario.Administrador);
							break;
						case 2:
							usuario.setTipoUsuario(TiposUsuario.Usuario);
							break;
						}
						ctrl.registro(usuario);
						response.sendRedirect("SrvListarUsuario");
					} else {
						System.out.println("User o email ya registrado. Intentar con otro.");
						response.setStatus(404);
						//request.setAttribute("error", "User o email ya registrado. Intentar con otro.");
						request.setAttribute("messageError", "User o email ya registrado. Intentar con otro.");
						request.getRequestDispatcher("formUsuario.jsp").forward(request, response);
						//request.getRequestDispatcher("error.jsp").forward(request, response);
					
				} 
				}catch (Exception e) {
					response.setStatus(404);
					request.setAttribute("error", e.getMessage());					
					request.getRequestDispatcher("error.jsp").forward(request, response);
				}
			}
				
		
	}

}
