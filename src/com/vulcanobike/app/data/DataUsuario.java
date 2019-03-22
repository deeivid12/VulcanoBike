package com.vulcanobike.app.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.vulcanobike.app.entities.ItemPedido;
import com.vulcanobike.app.entities.Producto;
import com.vulcanobike.app.entities.Usuario;
import com.vulcanobike.app.entities.Usuario.TiposUsuario;

public class DataUsuario {
	
	public Usuario login(String user, String pass){
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
				
				switch(rs.getInt("tipo_usuario")){
					case 1: usuario.setTipoUsuario(TiposUsuario.Administrador); break;
					case 2: usuario.setTipoUsuario(TiposUsuario.Usuario); break;
				}
			}
		} catch(SQLException e){
			e.printStackTrace();
		} 
		finally{
			
			FactoryConexion.getInstancia().releaseConn();
			if(stmt != null) stmt = null;
			if(rs!=null) rs = null;
		}
		return usuario;
	}
	
	
	public void Insert(Usuario usuario){
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
			e.printStackTrace();
		} 
		finally{
			FactoryConexion.getInstancia().releaseConn();
			if(stmt != null) stmt = null;
			if(rs!=null) rs = null;
		}
		
	}
	
	
	public Usuario GetOneByUser(String user){
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
				
				switch(rs.getInt("tipo_usuario")){
					case 1: usuario.setTipoUsuario(TiposUsuario.Administrador); break;
					case 2: usuario.setTipoUsuario(TiposUsuario.Usuario); break;
				}
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		finally{
			FactoryConexion.getInstancia().releaseConn();
			if(stmt != null) stmt = null;
			if(rs!=null) rs = null;
		}
		return usuario;
	}
	
	
	public Usuario GetOneByEmail(String email){
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
				
				switch(rs.getInt("tipo_usuario")){
					case 1: usuario.setTipoUsuario(TiposUsuario.Administrador); break;
					case 2: usuario.setTipoUsuario(TiposUsuario.Usuario); break;
				}
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		finally{
			FactoryConexion.getInstancia().releaseConn();
			if(stmt != null) stmt = null;
			if(rs!=null) rs = null;
		}
		return usuario;
	}
	

}
