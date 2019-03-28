package com.vulcanobike.app.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.vulcanobike.app.entities.Aplicacion;
import com.vulcanobike.app.entities.Marca;
import com.vulcanobike.app.entities.Producto;
import com.vulcanobike.app.entities.Rodado;
import com.vulcanobike.app.entities.TipoProducto;

public class DataProducto {

	public ArrayList<Producto> GetAll(){
		ArrayList<Producto> list = new ArrayList<Producto>();
		Producto p = null; ResultSet rs = null; PreparedStatement stmt = null; TipoProducto tp = null;
		Aplicacion a = null; Rodado r = null; Marca m =null;
		String sql="select * from productos left join tipo_producto on productos.id_tipo_producto = tipo_producto.id"
				+ "							left join aplicaciones on productos.id_aplicacion = aplicaciones.id"
				+ "							left join rodados on productos.id_rodado = rodados.id"
				+ "							left join marcas on productos.id_marca = marcas.id";
		try{
			Connection conn = FactoryConexion.getInstancia().getConn();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next() && rs!=null){
				p = new Producto();
				tp = new TipoProducto();
				a = new Aplicacion();
				r = new Rodado();
				m = new Marca();
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
				m.setId(rs.getInt("marcas.id"));
				m.setNombre(rs.getString("marcas.nombre"));
				m.setDescripcion(rs.getString("marcas.descripcion"));
				m.setOrigen(rs.getString("marcas.origen"));
				p.setTipoProducto(tp);
				p.setAplicacionBicicleta(a);
				p.setRodado(r);
				p.setMarca(m);
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
		Producto p = null; ResultSet rs = null; PreparedStatement stmt = null; TipoProducto tp = null;
		Aplicacion a = null; Rodado r = null; Marca m =null;
		String sql="select * from productos left join tipo_producto on productos.id_tipo_producto = tipo_producto.id"
				+ "							left join aplicaciones on productos.id_aplicacion = aplicaciones.id"
				+ "							left join rodados on productos.id_rodado = rodados.id"
				+ "							left join marcas on productos.id_marca = marcas.id"
				+ "							where productos.id=?";
		try{
			Connection conn = FactoryConexion.getInstancia().getConn();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			while(rs.next() && rs!=null){
				p = new Producto();
				tp = new TipoProducto();
				a = new Aplicacion();
				r = new Rodado();
				m = new Marca();
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
				m.setId(rs.getInt("marcas.id"));
				m.setNombre(rs.getString("marcas.nombre"));
				m.setDescripcion(rs.getString("marcas.descripcion"));
				m.setOrigen(rs.getString("marcas.origen"));
				p.setTipoProducto(tp);
				p.setAplicacionBicicleta(a);
				p.setRodado(r);
				p.setMarca(m);
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
		String sql="UPDATE productos SET nombre=?, descripcion=?, precio=?, stock=?, id_tipo_producto=?, id_rodado=?, id_aplicacion=?, id_marca=? WHERE id=?";
		try{
			Connection conn = FactoryConexion.getInstancia().getConn();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, p.getNombre()); 
			stmt.setString(2, p.getDescripcion());
			stmt.setFloat(3, p.getPrecio());
			stmt.setInt(4, p.getStock());
			stmt.setInt(5, p.getTipoProducto().getId());
			stmt.setInt(6, p.getRodado().getId());
			stmt.setInt(7, p.getAplicacionBicicleta().getId());
			stmt.setInt(8, p.getMarca().getId());
			stmt.setInt(9, p.getId());
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
	
	public void Insert(Producto p) throws Exception{
		ResultSet rs = null; PreparedStatement stmt = null;
		String sql="insert into productos (nombre, descripcion, precio, stock, imagen, id_tipo_producto, id_rodado, id_aplicacion, id_marca) values (?,?,?,?,?,?,?,?,?)";		
		try{
			Connection conn = FactoryConexion.getInstancia().getConn();
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, p.getNombre()); 
			stmt.setString(2, p.getDescripcion());
			stmt.setFloat(3, p.getPrecio());
			stmt.setInt(4, p.getStock());
			stmt.setString(5, p.getImagen());
			stmt.setInt(6, p.getTipoProducto().getId());
			stmt.setInt(7, p.getRodado().getId());
			stmt.setInt(8, p.getAplicacionBicicleta().getId());
			stmt.setInt(9, p.getMarca().getId());
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
	
	public void Delete(int id) throws Exception{
		ResultSet rs = null; PreparedStatement stmt = null;
		String sql="DELETE from productos where id=?";
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
