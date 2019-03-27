package com.vulcanobike.app.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.vulcanobike.app.entities.Aplicacion;
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
}
