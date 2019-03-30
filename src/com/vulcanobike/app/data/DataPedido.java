package com.vulcanobike.app.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.vulcanobike.app.entities.Pedido;
import com.vulcanobike.app.entities.TipoProducto;
import com.vulcanobike.app.entities.Usuario;
import com.vulcanobike.app.entities.Pedido.EstadosPedido;
import com.vulcanobike.app.entities.Usuario.TiposUsuario;

public class DataPedido {
	
	public ArrayList<Pedido> GetAll() throws Exception{
		ArrayList<Pedido> list = new ArrayList<Pedido>();
		Pedido p = null; ResultSet rs = null; PreparedStatement stmt = null; Usuario u;
		String sql="select * from pedidos inner join usuarios on pedidos.id_usuario = usuarios.id";
		try{
			Connection conn = FactoryConexion.getInstancia().getConn();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next() && rs!=null){
				p = new Pedido();
				u = new Usuario();
				p.setId(rs.getInt("id"));
				p.setFechaEmision(rs.getDate("fecha_emision").toString());
				p.setImporte(rs.getFloat("importe"));
				switch(rs.getInt("estado")) {
				case 1: p.setEstado(EstadosPedido.Pendiente); break;
				case 2: p.setEstado(EstadosPedido.En_Proceso); break;
				case 3: p.setEstado(EstadosPedido.Enviado); break;
				}
				u.setId(rs.getInt("usuarios.id"));
				u.setNombre(rs.getString("usuarios.nombre"));
				u.setApellido(rs.getString("usuarios.apellido"));
				u.setEmail(rs.getString("usuarios.email"));
				u.setDireccion(rs.getString("usuarios.direccion"));
				u.setTelefono(rs.getString("usuarios.telefono"));
				u.setUser(rs.getString("usuarios.user"));
				u.setPassword(rs.getString("usuarios.password"));
				switch (rs.getInt("tipo_usuario")) {
				case 1: u.setTipoUsuario(TiposUsuario.Administrador);
				case 2: u.setTipoUsuario(TiposUsuario.Usuario);
				}
				p.setUsuario(u);
				list.add(p);
			}
		} catch(SQLException e){
			e.printStackTrace();
			throw new Exception("Error al recuperar datos",e);
		}
		finally{
			FactoryConexion.getInstancia().releaseConn();
			if(stmt != null) stmt = null;
			if(rs!=null) rs = null;
		}
		return list;
	}
	
	
	public Pedido GetOne(Integer id) throws Exception{
		Pedido p = null; ResultSet rs = null; PreparedStatement stmt = null; Usuario u;
		String sql="select * from pedidos inner join usuarios on pedidos.id_usuario = usuarios.id where pedidos.id=?";
		try{
			Connection conn = FactoryConexion.getInstancia().getConn();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			while(rs.next() && rs!=null){
				p = new Pedido();
				u = new Usuario();
				p.setId(rs.getInt("id"));
				p.setFechaEmision(rs.getDate("fecha_emision").toString());
				p.setImporte(rs.getFloat("importe"));
				switch(rs.getInt("estado")) {
				case 1: p.setEstado(EstadosPedido.Pendiente); break;
				case 2: p.setEstado(EstadosPedido.En_Proceso); break;
				case 3: p.setEstado(EstadosPedido.Enviado); break;
				}
				u.setId(rs.getInt("usuarios.id"));
				u.setNombre(rs.getString("usuarios.nombre"));
				u.setApellido(rs.getString("usuarios.apellido"));
				u.setEmail(rs.getString("usuarios.email"));
				u.setDireccion(rs.getString("usuarios.direccion"));
				u.setTelefono(rs.getString("usuarios.telefono"));
				u.setUser(rs.getString("usuarios.user"));
				u.setPassword(rs.getString("usuarios.password"));
				switch (rs.getInt("tipo_usuario")) {
				case 1: u.setTipoUsuario(TiposUsuario.Administrador); break;
				case 2: u.setTipoUsuario(TiposUsuario.Usuario); break;
				}
				p.setUsuario(u);
			}
		} catch(SQLException e){
			//e.printStackTrace();
			throw new Exception("Error al recuperar datos.", e);
		}
		finally{
			FactoryConexion.getInstancia().releaseConn();
			if(stmt != null) stmt = null;
			if(rs!=null) rs = null;
		}
		return p;
	}
	
	
	public int Insert(Pedido p){
		ResultSet rs = null; PreparedStatement stmt = null;
		String sql="insert into pedidos (importe, id_usuario, estado, fecha_emision) values (?,?,?,?)";		
		int id = 0;
		
		try{
			Connection conn = FactoryConexion.getInstancia().getConn();
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			stmt.setFloat(1, p.getImporte()); 
			stmt.setInt(2, p.getUsuario().getId());
			switch(p.getEstado()) {
			case Pendiente: stmt.setInt(3, 1); break;
			case En_Proceso: stmt.setInt(3, 2); break;
			case Enviado: stmt.setInt(3, 3);break;
			}
			stmt.setString(4, p.getFechaEmision());
			stmt.execute();
			rs = stmt.getGeneratedKeys();
			if(rs.next()) {
				id = rs.getInt(1);
			}
			
			
			
			
		} catch(SQLException e){
			e.printStackTrace();
		} 
		finally{
			FactoryConexion.getInstancia().releaseConn();
			if(stmt != null) stmt = null;
			if(rs!=null) rs = null;
		}
		
		return id;

	}
	
	
	public void Update(Pedido p) throws Exception{
		ResultSet rs = null; PreparedStatement stmt = null;
		String sql="UPDATE pedidos SET estado=? WHERE id=?";
		try{
			Connection conn = FactoryConexion.getInstancia().getConn();
			stmt = conn.prepareStatement(sql);
			switch (p.getEstado()) {
			case Pendiente: stmt.setInt(1, 1); break;
			case En_Proceso: stmt.setInt(1, 2); break;
			case Enviado: stmt.setInt(1, 3); break;
			}
			stmt.setInt(2, p.getId());
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
	
	

}
