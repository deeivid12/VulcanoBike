package com.vulcanobike.app.data;

import java.sql.*;
import java.util.ArrayList;

import com.vulcanobike.app.entities.TipoProducto;

public class DataTipoProducto {
	
	public ArrayList<TipoProducto> GetAll() throws Exception{
		ArrayList<TipoProducto> list = new ArrayList<TipoProducto>();
		TipoProducto tp = null; ResultSet rs = null; PreparedStatement stmt = null;
		String sql="select * from tipo_producto";
		try{
			Connection conn = FactoryConexion.getInstancia().getConn();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next() && rs!=null){
				tp = new TipoProducto();
				tp.setId(rs.getInt("id"));
				System.out.println("IDDDDDD: " + tp.getId());
				tp.setNombre(rs.getString("nombre"));
				tp.setDescripcion(rs.getString("descripcion"));
				list.add(tp);
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
	
	public TipoProducto GetOne(Integer id){
		TipoProducto tp = null; ResultSet rs = null; PreparedStatement stmt = null;
		String sql="select * from tipo_producto where id=?";
		try{
			Connection conn = FactoryConexion.getInstancia().getConn();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			while(rs.next() && rs!=null){
				tp = new TipoProducto();
				tp.setId(rs.getInt("id"));
				tp.setNombre(rs.getString("nombre"));
				tp.setDescripcion(rs.getString("descripcion"));
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		finally{
			FactoryConexion.getInstancia().releaseConn();
			if(stmt != null) stmt = null;
			if(rs!=null) rs = null;
		}
		return tp;
	}
	
	
	public void Insert(TipoProducto tp){
		ResultSet rs = null; PreparedStatement stmt = null;
		String sql="insert into tipo_producto (nombre, descripcion) values (?,?)";		
		try{
			Connection conn = FactoryConexion.getInstancia().getConn();
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, tp.getNombre()); 
			stmt.setString(2, tp.getDescripcion());
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
	
	
	public void Update(TipoProducto tp){
		ResultSet rs = null; PreparedStatement stmt = null;
		String sql="UPDATE tipo_producto SET nombre=?, descripcion=? WHERE id=?";
		try{
			Connection conn = FactoryConexion.getInstancia().getConn();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, tp.getNombre()); 
			stmt.setString(2, tp.getDescripcion());
			stmt.setInt(3, tp.getId());
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
	
	
	public void Delete(int id){
		ResultSet rs = null; PreparedStatement stmt = null;
		String sql="DELETE from tipo_producto where id=?";
		try{
			Connection conn = FactoryConexion.getInstancia().getConn();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
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

}
