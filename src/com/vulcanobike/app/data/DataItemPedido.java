package com.vulcanobike.app.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.vulcanobike.app.entities.ItemPedido;
import com.vulcanobike.app.entities.Pedido;

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

}
