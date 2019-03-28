package com.vulcanobike.app.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.vulcanobike.app.entities.Aplicacion;
import com.vulcanobike.app.entities.TipoProducto;

public class DataAplicacion {
	
	public ArrayList<Aplicacion> GetAll() throws Exception{
		ArrayList<Aplicacion> list = new ArrayList<Aplicacion>();
		Aplicacion a = null; ResultSet rs = null; PreparedStatement stmt = null;
		String sql="select * from aplicaciones";
		try{
			Connection conn = FactoryConexion.getInstancia().getConn();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next() && rs!=null){
				a = new Aplicacion();
				a.setId(rs.getInt("id"));
				a.setNombre(rs.getString("nombre"));
				a.setDescripcion(rs.getString("descripcion"));
				list.add(a);
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
	
	
	public Aplicacion GetOne(Integer id) throws Exception{
		Aplicacion a = null; ResultSet rs = null; PreparedStatement stmt = null;
		String sql="select * from aplicaciones where id=?";
		try{
			Connection conn = FactoryConexion.getInstancia().getConn();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			while(rs.next() && rs!=null){
				a = new Aplicacion();
				a.setId(rs.getInt("id"));
				a.setNombre(rs.getString("nombre"));
				a.setDescripcion(rs.getString("descripcion"));
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
		return a;
	}
	
	
	public void Insert(Aplicacion a) throws Exception{
		ResultSet rs = null; PreparedStatement stmt = null;
		String sql="insert into aplicaciones (nombre, descripcion) values (?,?)";		
		try{
			Connection conn = FactoryConexion.getInstancia().getConn();
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, a.getNombre()); 
			stmt.setString(2, a.getDescripcion());
			stmt.execute();
			
		} catch(SQLException e){
			//e.printStackTrace();
			throw new Exception("Error al registrar datos.", e);
		} 
		finally{
			FactoryConexion.getInstancia().releaseConn();
			if(stmt != null) stmt = null;
			if(rs!=null) rs = null;
		}
	}
	
	
	public void Update(Aplicacion a) throws Exception{
		ResultSet rs = null; PreparedStatement stmt = null;
		String sql="UPDATE aplicaciones SET nombre=?, descripcion=? WHERE id=?";
		try{
			Connection conn = FactoryConexion.getInstancia().getConn();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, a.getNombre()); 
			stmt.setString(2, a.getDescripcion());
			stmt.setInt(3, a.getId());
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
	
	
	public void Delete(int id) throws Exception{
		ResultSet rs = null; PreparedStatement stmt = null;
		String sql="DELETE from aplicaciones where id=?";
		try{
			Connection conn = FactoryConexion.getInstancia().getConn();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.execute();
			
		} catch(SQLException e){
			//e.printStackTrace();
			throw new Exception("Error al eliminar datos.", e);
		}
		finally{
			FactoryConexion.getInstancia().releaseConn();
			if(stmt != null) stmt = null;
			if(rs!=null) rs = null;
		}
	}

}
