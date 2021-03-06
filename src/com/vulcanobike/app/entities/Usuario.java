package com.vulcanobike.app.entities;

public class Usuario {
	
	public static enum TiposUsuario{Administrador, Usuario}
	
	private int id;
	private String nombre;
	private String apellido;
	private String email;
	private String direccion;
	private String telefono;
	private String user;
	private String password;
	private TiposUsuario tipoUsuario;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public TiposUsuario getTipoUsuario() {
		return tipoUsuario;
	}
	public void setTipoUsuario(TiposUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
		

}
