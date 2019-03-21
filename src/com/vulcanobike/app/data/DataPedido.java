package com.vulcanobike.app.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.vulcanobike.app.entities.Pedido;
import com.vulcanobike.app.entities.TipoProducto;

public class DataPedido {
	
	public int Insert(Pedido p){
		ResultSet rs = null; PreparedStatement stmt = null;
		String sql="insert into pedidos (importe) values (?)";		
		int id = 0;
		
		try{
			Connection conn = FactoryConexion.getInstancia().getConn();
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			stmt.setFloat(1, p.getImporte()); 
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
