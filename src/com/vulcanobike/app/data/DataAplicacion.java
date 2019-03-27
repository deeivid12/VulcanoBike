package com.vulcanobike.app.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.vulcanobike.app.entities.Aplicacion;

public class DataAplicacion {
	
	public ArrayList<Aplicacion> GetAll() throws Exception{
		ArrayList<Aplicacion> list = new ArrayList<Aplicacion>();
		Aplicacion a = null; ResultSet rs = null; PreparedStatement stmt = null;
		String sql="select * from aplicaciones";
		try{
			Connection conn = FactoryConexion.getInstancia().getConn();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next() && rs!=null){
				a = new Aplicacion();
				a.setId(rs.getInt("id"));
				a.setNombre(rs.getString("nombre"));
				a.setDescripcion(rs.getString("descripcion"));
				list.add(a);
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
