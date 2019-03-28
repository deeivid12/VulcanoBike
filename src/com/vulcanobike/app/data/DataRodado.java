package com.vulcanobike.app.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.vulcanobike.app.entities.Rodado;

public class DataRodado {
	
	
	public ArrayList<Rodado> GetAll() throws Exception{
		ArrayList<Rodado> list = new ArrayList<Rodado>();
		Rodado r = null; ResultSet rs = null; PreparedStatement stmt = null;
		String sql="select * from rodados";
		try{
			Connection conn = FactoryConexion.getInstancia().getConn();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next() && rs!=null){
				r = new Rodado();
				r.setId(rs.getInt("id"));
				r.setNombre(rs.getString("nombre"));
				r.setDescripcion(rs.getString("descripcion"));
				list.add(r);
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
	
	
	public Rodado GetOne(Integer id) throws Exception{
		Rodado r = null; ResultSet rs = null; PreparedStatement stmt = null;
		String sql="select * from rodados where id=?";
		try{
			Connection conn = FactoryConexion.getInstancia().getConn();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			while(rs.next() && rs!=null){
				r = new Rodado();
				r.setId(rs.getInt("id"));
				r.setNombre(rs.getString("nombre"));
				r.setDescripcion(rs.getString("descripcion"));
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
		return r;
	}
	
	
	public void Insert(Rodado r) throws Exception{
		ResultSet rs = null; PreparedStatement stmt = null;
		String sql="insert into rodados (nombre, descripcion) values (?,?)";		
		try{
			Connection conn = FactoryConexion.getInstancia().getConn();
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, r.getNombre()); 
			stmt.setString(2, r.getDescripcion());
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
	
	
	public void Update(Rodado r) throws Exception{
		ResultSet rs = null; PreparedStatement stmt = null;
		String sql="UPDATE rodados SET nombre=?, descripcion=? WHERE id=?";
		try{
			Connection conn = FactoryConexion.getInstancia().getConn();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, r.getNombre()); 
			stmt.setString(2, r.getDescripcion());
			stmt.setInt(3, r.getId());
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
		String sql="DELETE from rodados where id=?";
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
