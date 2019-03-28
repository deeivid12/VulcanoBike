package com.vulcanobike.app.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.vulcanobike.app.entities.Marca;

public class DataMarca {
	
	public ArrayList<Marca> GetAll() throws Exception{
		ArrayList<Marca> list = new ArrayList<Marca>();
		Marca m = null; ResultSet rs = null; PreparedStatement stmt = null;
		String sql="select * from marcas";
		try{
			Connection conn = FactoryConexion.getInstancia().getConn();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next() && rs!=null){
				m = new Marca();
				m.setId(rs.getInt("id"));
				m.setNombre(rs.getString("nombre"));
				m.setDescripcion(rs.getString("descripcion"));
				m.setOrigen(rs.getString("origen"));
				list.add(m);
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
	
	
	public Marca GetOne(Integer id) throws Exception{
		Marca m = null; ResultSet rs = null; PreparedStatement stmt = null;
		String sql="select * from marcas where id=?";
		try{
			Connection conn = FactoryConexion.getInstancia().getConn();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			while(rs.next() && rs!=null){
				m = new Marca();
				m.setId(rs.getInt("id"));
				m.setNombre(rs.getString("nombre"));
				m.setDescripcion(rs.getString("descripcion"));
				m.setOrigen(rs.getString("origen"));
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
		return m;
	}
	
	
	public void Insert(Marca m) throws Exception{
		ResultSet rs = null; PreparedStatement stmt = null;
		String sql="insert into marcas (nombre, descripcion, origen) values (?,?, ?)";		
		try{
			Connection conn = FactoryConexion.getInstancia().getConn();
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, m.getNombre()); 
			stmt.setString(2, m.getDescripcion());
			stmt.setString(3, m.getOrigen());
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
	
	
	public void Update(Marca m) throws Exception{
		ResultSet rs = null; PreparedStatement stmt = null;
		String sql="UPDATE marcas SET nombre=?, descripcion=?, origen=? WHERE id=?";
		try{
			Connection conn = FactoryConexion.getInstancia().getConn();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, m.getNombre()); 
			stmt.setString(2, m.getDescripcion());
			stmt.setString(3, m.getOrigen());
			stmt.setInt(4, m.getId());
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
		String sql="DELETE from marcas where id=?";
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
