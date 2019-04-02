package com.vulcanobike.app.data;

import java.sql.*;

public class FactoryConexion {
	
	/*
	private String dbDriver="com.mysql.cj.jdbc.Driver";
	private String host="localhost";
	private String port="3306";
	private String user="root";
	private String pass="mysql2019";
	private String db="VulcanoBike_db";
	*/
	
	
	private String dbDriver="com.mysql.cj.jdbc.Driver";
	private String port="3306";
	private String db="vulcanobike_db";
	private String dbType="mysql";
	private String user="root";
	private String pass="YSBiqt95719";
	private String host="node30316-vulcanobike.jelastic.saveincloud.net";
	
	
	
	private Connection conn;
	private int cantCon;
	
	
	private FactoryConexion(){  //constructor
		try {
			Class.forName(dbDriver);
			conn=null;
			cantCon=0;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private static FactoryConexion instancia; //declara variable
	
	public static FactoryConexion getInstancia(){
		if (instancia==null){
			instancia = new FactoryConexion();
		}
		return instancia;
	}
	
	
	public Connection getConn(){
		try {
			if(conn==null || conn.isClosed()){
				//conn = DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/"+db+"?user="+user+"&password="+pass+"&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
				conn = DriverManager.getConnection("jdbc:"+dbType+"://"+host+":"+port+"/"+db, user, pass);
				cantCon++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	
	
	public void releaseConn(){
		try {
			cantCon--;
			if(cantCon==0){
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
