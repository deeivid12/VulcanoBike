package com.vulcanobike.app.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.vulcanobike.app.entities.ItemPedido;
import com.vulcanobike.app.entities.Producto;
import com.vulcanobike.app.entities.Rodado;
import com.vulcanobike.app.entities.Usuario;
import com.vulcanobike.app.entities.Usuario.TiposUsuario;

public class DataUsuario {
	
	public Usuario login(String user, String pass) throws Exception{
		Usuario usuario = null; ResultSet rs = null; PreparedStatement stmt = null;
		String sql="SELECT * from usuarios where user=? and password=?";
		try{
			Connection conn = FactoryConexion.getInstancia().getConn();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, user);
			stmt.setString(2, pass);
			rs = stmt.executeQuery();
			while(rs.next() && rs!=null){
				usuario = new Usuario();
				usuario.setId(rs.getInt("id"));
				usuario.setNombre(rs.getString("nombre"));
				usuario.setApellido(rs.getString("apellido"));
				usuario.setPassword(rs.getString("password"));
				usuario.setEmail(rs.getString("email"));
				usuario.setUser(rs.getString("user"));
				usuario.setDireccion(rs.getString("direccion"));
				usuario.setTelefono(rs.getString("telefono"));
				
				switch(rs.getInt("tipo_usuario")){
					case 1: usuario.setTipoUsuario(TiposUsuario.Administrador); break;
					case 2: usuario.setTipoUsuario(TiposUsuario.Usuario); break;
				}
			}
		} catch(SQLException e){
			//e.printStackTrace();
			throw new Exception("Error al recuperar datos.",e);
		} 
		finally{
			
			FactoryConexion.getInstancia().releaseConn();
			if(stmt != null) stmt = null;
			if(rs!=null) rs = null;
		}
		return usuario;
	}
	
	
	public void Insert(Usuario usuario) throws Exception{
		ResultSet rs = null; PreparedStatement stmt = null;
		String sql="insert into usuarios (nombre, apellido, email, direccion, telefono, user, password, tipo_usuario) values (?,?,?,?,?,?,?,?)";		
		
		try{
			Connection conn = FactoryConexion.getInstancia().getConn();
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, usuario.getNombre()); 
			stmt.setString(2, usuario.getApellido());
			stmt.setString(3, usuario.getEmail());
			stmt.setString(4, usuario.getDireccion());
			stmt.setString(5, usuario.getTelefono());
			stmt.setString(6, usuario.getUser());
			stmt.setString(7, usuario.getPassword()); 
			switch(usuario.getTipoUsuario()){
				case Administrador: stmt.setInt(8, 1); break;
				case Usuario: stmt.setInt(8, 2); break;				
			}			
			stmt.execute();
					
			
		} catch(SQLException e){
			//e.printStackTrace();
			throw new Exception("Error al insertar datos.",e);
		} 
		finally{
			FactoryConexion.getInstancia().releaseConn();
			if(stmt != null) stmt = null;
			if(rs!=null) rs = null;
		}
		
	}
	
	
	public Usuario GetOneByUser(String user) throws Exception{
		Usuario usuario = null; ResultSet rs = null; PreparedStatement stmt = null;
		String sql="select * from usuarios where user=?";
		try{
			Connection conn = FactoryConexion.getInstancia().getConn();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, user);
			rs = stmt.executeQuery();
			while(rs.next() && rs!=null){
				usuario = new Usuario();
				usuario.setId(rs.getInt("id"));
				usuario.setNombre(rs.getString("nombre"));
				usuario.setApellido(rs.getString("apellido"));
				usuario.setPassword(rs.getString("password"));
				usuario.setEmail(rs.getString("email"));
				usuario.setUser(rs.getString("user"));
				usuario.setDireccion(rs.getString("direccion"));
				usuario.setTelefono(rs.getString("telefono"));
				
				switch(rs.getInt("tipo_usuario")){
					case 1: usuario.setTipoUsuario(TiposUsuario.Administrador); break;
					case 2: usuario.setTipoUsuario(TiposUsuario.Usuario); break;
				}
			}
		} catch(SQLException e){
			//e.printStackTrace();
			throw new Exception("Error al recuperar datos.",e);
		}
		finally{
			FactoryConexion.getInstancia().releaseConn();
			if(stmt != null) stmt = null;
			if(rs!=null) rs = null;
		}
		return usuario;
	}
	
	
	public Usuario GetOneByEmail(String email) throws Exception{
		Usuario usuario = null; ResultSet rs = null; PreparedStatement stmt = null;
		String sql="select * from usuarios where email=?";
		try{
			Connection conn = FactoryConexion.getInstancia().getConn();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, email);
			rs = stmt.executeQuery();
			while(rs.next() && rs!=null){
				usuario = new Usuario();
				usuario.setId(rs.getInt("id"));
				usuario.setNombre(rs.getString("nombre"));
				usuario.setApellido(rs.getString("apellido"));
				usuario.setPassword(rs.getString("password"));
				usuario.setEmail(rs.getString("email"));
				usuario.setUser(rs.getString("user"));
				usuario.setDireccion(rs.getString("direccion"));
				usuario.setTelefono(rs.getString("telefono"));
				
				switch(rs.getInt("tipo_usuario")){
					case 1: usuario.setTipoUsuario(TiposUsuario.Administrador); break;
					case 2: usuario.setTipoUsuario(TiposUsuario.Usuario); break;
				}
			}
		} catch(SQLException e){
			//e.printStackTrace();
			throw new Exception("Error al recuperar datos.",e);
		}
		finally{
			FactoryConexion.getInstancia().releaseConn();
			if(stmt != null) stmt = null;
			if(rs!=null) rs = null;
		}
		return usuario;
	}
	
	
	public List<Usuario> GetAll() throws Exception{
		ArrayList<Usuario> list = new ArrayList<Usuario>();
		Usuario usuario = null; ResultSet rs = null; PreparedStatement stmt = null;
		String sql="select * from usuarios";
		try{
			Connection conn = FactoryConexion.getInstancia().getConn();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next() && rs!=null){
				usuario = new Usuario();
				usuario.setId(rs.getInt("id"));
				usuario.setNombre(rs.getString("nombre"));
				usuario.setApellido(rs.getString("apellido"));
				usuario.setPassword(rs.getString("password"));
				usuario.setEmail(rs.getString("email"));
				usuario.setUser(rs.getString("user"));
				usuario.setDireccion(rs.getString("direccion"));
				usuario.setTelefono(rs.getString("telefono"));
				
				switch(rs.getInt("tipo_usuario")){
					case 1: usuario.setTipoUsuario(TiposUsuario.Administrador); break;
					case 2: usuario.setTipoUsuario(TiposUsuario.Usuario); break;
				}
				
				list.add(usuario);
			}
		} catch(SQLException e){
			//e.printStackTrace();
			throw new Exception("Error al recuperar datos.",e);
		}
		finally{
			FactoryConexion.getInstancia().releaseConn();
			if(stmt != null) stmt = null;
			if(rs!=null) rs = null;
		}
		return list;
	}
	
	
	public void Update(Usuario u) throws Exception{
		ResultSet rs = null; PreparedStatement stmt = null;
		String sql="UPDATE usuarios SET nombre=?, apellido=?, email=?, direccion=?, telefono=?, user=?, password=?, tipo_usuario=? WHERE id=?";
		try{
			Connection conn = FactoryConexion.getInstancia().getConn();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, u.getNombre()); 
			stmt.setString(2, u.getApellido());
			stmt.setString(3, u.getEmail());
			stmt.setString(4, u.getDireccion());
			stmt.setString(5, u.getTelefono());
			stmt.setString(6, u.getUser());
			stmt.setString(7, u.getPassword()); 
			switch(u.getTipoUsuario()){
				case Administrador: stmt.setInt(8, 1); break;
				case Usuario: stmt.setInt(8, 2); break;				
			}	
			stmt.setInt(9, u.getId()); 
			stmt.execute();
			
		} catch(SQLException e){
			//e.printStackTrace();
			throw new Exception("Error al actualizar datos.", e);

		}
		finally{
		
			FactoryConexion.getInstancia().releaseConn();
			if(stmt != null) stmt = null;
			if(rs!=null) rs = null;
		}
	}
	
	
	public Usuario GetOne(int id) throws Exception{
		Usuario usuario = null; ResultSet rs = null; PreparedStatement stmt = null;
		String sql="select * from usuarios where id=?";
		try{
			Connection conn = FactoryConexion.getInstancia().getConn();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			while(rs.next() && rs!=null){
				usuario = new Usuario();
				usuario.setId(rs.getInt("id"));
				usuario.setNombre(rs.getString("nombre"));
				usuario.setApellido(rs.getString("apellido"));
				usuario.setPassword(rs.getString("password"));
				usuario.setEmail(rs.getString("email"));
				usuario.setUser(rs.getString("user"));
				usuario.setDireccion(rs.getString("direccion"));
				usuario.setTelefono(rs.getString("telefono"));
				
				switch(rs.getInt("tipo_usuario")){
					case 1: usuario.setTipoUsuario(TiposUsuario.Administrador); break;
					case 2: usuario.setTipoUsuario(TiposUsuario.Usuario); break;
				}
			}
		} catch(SQLException e){
			//e.printStackTrace();
			throw new Exception("Error al recuperar datos.",e);
		}
		finally{
			FactoryConexion.getInstancia().releaseConn();
			if(stmt != null) stmt = null;
			if(rs!=null) rs = null;
		}
		return usuario;
	}
	
	
	public void Delete(int id) throws Exception{
		ResultSet rs = null; PreparedStatement stmt = null;
		String sql="DELETE from usuarios where id=?";
		try{
			Connection conn = FactoryConexion.getInstancia().getConn();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.execute();
			
		} catch(SQLException e){
			e.printStackTrace();
			throw new Exception("Error al eliminar datos.", e);
		}
		finally{
			FactoryConexion.getInstancia().releaseConn();
			if(stmt != null) stmt = null;
			if(rs!=null) rs = null;
		}
	}
	

}
