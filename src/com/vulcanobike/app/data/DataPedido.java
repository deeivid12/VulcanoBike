package com.vulcanobike.app.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.vulcanobike.app.entities.Pedido;
import com.vulcanobike.app.entities.TipoProducto;

public class DataPedido {
	
	public ArrayList<Pedido> GetAll() throws Exception{
		ArrayList<Pedido> list = new ArrayList<Pedido>();
		Pedido p = null; ResultSet rs = null; PreparedStatement stmt = null;
		String sql="select * from pedidos";
		try{
			Connection conn = FactoryConexion.getInstancia().getConn();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next() && rs!=null){
				p = new Pedido();
				p.setId(rs.getInt("id"));
				//p.setFechaEmision
				p.setImporte(rs.getFloat("importe"));
				p.getUsuario().setId((rs.getInt("id_usuario")));
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
	
	
	public int Insert(Pedido p){
		ResultSet rs = null; PreparedStatement stmt = null;
		String sql="insert into pedidos (importe, id_usuario) values (?,?)";		
		int id = 0;
		
		try{
			Connection conn = FactoryConexion.getInstancia().getConn();
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			stmt.setFloat(1, p.getImporte()); 
			stmt.setInt(2, p.getUsuario().getId());
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
	
	

}
