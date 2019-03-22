package com.vulcanobike.app.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.vulcanobike.app.entities.Producto;
import com.vulcanobike.app.entities.TipoProducto;

public class DataProducto {

	public ArrayList<Producto> GetAll(){
		ArrayList<Producto> list = new ArrayList<Producto>();
		Producto p = null; ResultSet rs = null; PreparedStatement stmt = null;
		String sql="select * from productos";
		try{
			Connection conn = FactoryConexion.getInstancia().getConn();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next() && rs!=null){
				p = new Producto();
				p.setId(rs.getInt("id"));
				p.setNombre(rs.getString("nombre"));
				p.setDescripcion(rs.getString("descripcion"));
				p.setPrecio(rs.getFloat("precio"));
				p.setStock(rs.getInt("stock"));
				p.setImagen(rs.getString("imagen"));
				//AGREGAR LOS QUE FALTAN!
				list.add(p);
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		finally{
			FactoryConexion.getInstancia().releaseConn();
			if(stmt != null) stmt = null;
			if(rs!=null) rs = null;
		}
		return list;
	}
	
	
	public Producto GetOne(Integer id){
		Producto p = null; ResultSet rs = null; PreparedStatement stmt = null;
		String sql="select * from productos where id=?";
		try{
			Connection conn = FactoryConexion.getInstancia().getConn();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			while(rs.next() && rs!=null){
				p = new Producto();
				p.setId(rs.getInt("id"));
				p.setNombre(rs.getString("nombre"));
				p.setDescripcion(rs.getString("descripcion"));
				p.setPrecio(rs.getFloat("precio"));
				p.setStock(rs.getInt("stock"));
				p.setImagen(rs.getString("imagen"));
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		finally{
			FactoryConexion.getInstancia().releaseConn();
			if(stmt != null) stmt = null;
			if(rs!=null) rs = null;
		}
		return p;
	}
	
	
	public void Update(Producto p){
		ResultSet rs = null; PreparedStatement stmt = null;
		String sql="UPDATE productos SET nombre=?, descripcion=?, precio=?, stock=? WHERE id=?";
		try{
			Connection conn = FactoryConexion.getInstancia().getConn();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, p.getNombre()); 
			stmt.setString(2, p.getDescripcion());
			stmt.setDouble(3, p.getPrecio());
			stmt.setInt(4, p.getStock());
			stmt.setInt(5, p.getId());
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
