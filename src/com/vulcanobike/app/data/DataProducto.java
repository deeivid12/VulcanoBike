package com.vulcanobike.app.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.vulcanobike.app.entities.Aplicacion;
import com.vulcanobike.app.entities.Producto;
import com.vulcanobike.app.entities.Rodado;
import com.vulcanobike.app.entities.TipoProducto;

public class DataProducto {

	public ArrayList<Producto> GetAll(){
		ArrayList<Producto> list = new ArrayList<Producto>();
		Producto p = null; ResultSet rs = null; PreparedStatement stmt = null; TipoProducto tp = null;
		Aplicacion a = null; Rodado r = null;
		String sql="select * from productos inner join tipo_producto on productos.id_tipo_producto = tipo_producto.id"
				+ "							inner join aplicaciones on productos.id_aplicacion = aplicaciones.id"
				+ "							inner join rodados on productos.id_rodado = rodados.id";
		try{
			Connection conn = FactoryConexion.getInstancia().getConn();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next() && rs!=null){
				p = new Producto();
				tp = new TipoProducto();
				a = new Aplicacion();
				r = new Rodado();
				p.setId(rs.getInt("id"));
				p.setNombre(rs.getString("nombre"));
				p.setDescripcion(rs.getString("descripcion"));
				p.setPrecio(rs.getFloat("precio"));
				p.setStock(rs.getInt("stock"));
				p.setImagen(rs.getString("imagen"));
				tp.setId(rs.getInt("tipo_producto.id"));
				tp.setNombre(rs.getString("tipo_producto.nombre"));
				tp.setDescripcion(rs.getString("tipo_producto.descripcion"));
				a.setId(rs.getInt("aplicaciones.id"));
				a.setNombre(rs.getString("aplicaciones.nombre"));
				a.setDescripcion(rs.getString("aplicaciones.descripcion"));
				r.setId(rs.getInt("rodados.id"));
				r.setNombre(rs.getString("rodados.nombre"));
				r.setDescripcion(rs.getString("rodados.descripcion"));
				p.setTipoProducto(tp);
				p.setAplicacionBicicleta(a);
				p.setRodado(r);
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
				//completar lo que falta
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
