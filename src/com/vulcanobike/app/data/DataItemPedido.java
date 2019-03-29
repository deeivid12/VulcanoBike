package com.vulcanobike.app.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.vulcanobike.app.entities.ItemPedido;
import com.vulcanobike.app.entities.Pedido;
import com.vulcanobike.app.entities.Producto;
import com.vulcanobike.app.entities.TipoProducto;

import sun.java2d.pipe.ParallelogramPipe;

public class DataItemPedido {
	
	public void Insert(ItemPedido ip){
		ResultSet rs = null; PreparedStatement stmt = null;
		String sql="insert into items_pedidos (id_pedido, id_producto, cantidad, importe) values (?,?,?,?)";		
		
		try{
			Connection conn = FactoryConexion.getInstancia().getConn();
			stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, ip.getIdPedido()); 
			stmt.setInt(2, ip.getProducto().getId());
			stmt.setInt(3,  ip.getCantidad());
			stmt.setFloat(4, ip.getImporte());
			
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
	
	
	public ArrayList<ItemPedido> GetAll(int id) throws Exception{
		ArrayList<ItemPedido> list = new ArrayList<ItemPedido>();
		ItemPedido ip = null; ResultSet rs = null; PreparedStatement stmt = null; Producto p;
		String sql="select * from items_pedidos inner join productos on items_pedidos.id_producto = productos.id where id=?";
		try{
			Connection conn = FactoryConexion.getInstancia().getConn();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			while(rs.next() && rs!=null){
				ip = new ItemPedido();
				p = new Producto();
				ip.setCantidad(rs.getInt("cantidad"));
				ip.setIdPedido(rs.getInt("id_pedido"));
				ip.setImporte(rs.getFloat("importe"));
				p.setId(rs.getInt("productos.id"));
				p.setNombre(rs.getString("productos.nombre"));
				p.setDescripcion(rs.getString("productos.descripcion"));
				p.setPrecio(rs.getFloat("productos.precio"));
				p.setStock(rs.getInt("productos.stock"));
				p.setImagen(rs.getString("productos.imagen"));
				ip.setProducto(p);
				list.add(ip);
			}
		} catch(SQLException e){
			e.printStackTrace();
			throw new Exception("Error al recuperar datos.",e);
		}
		finally{
			FactoryConexion.getInstancia().releaseConn();
			if(stmt != null) stmt = null;
			if(rs!=null) rs = null;
		}
		return list;
	}
	

}
