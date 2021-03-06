package com.vulcanobike.app.entities;

public class Producto {
	
	private int id;
	private String nombre;
	private String descripcion;
	private float precio;
	private int stock;
	private TipoProducto tipoProducto;
	private Marca marca;
	private Rodado rodado;
	private Aplicacion aplicacionBicicleta;
	private String imagen;
	
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
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public TipoProducto getTipoProducto() {
		return tipoProducto;
	}
	public void setTipoProducto(TipoProducto tipoProducto) {
		this.tipoProducto = tipoProducto;
	}
	public Marca getMarca() {
		return marca;
	}
	public void setMarca(Marca marca) {
		this.marca = marca;
	}
	public Rodado getRodado() {
		return rodado;
	}
	public void setRodado(Rodado rodado) {
		this.rodado = rodado;
	}
	public Aplicacion getAplicacionBicicleta() {
		return aplicacionBicicleta;
	}
	public void setAplicacionBicicleta(Aplicacion aplicacionBicicleta) {
		this.aplicacionBicicleta = aplicacionBicicleta;
	}
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	
	
	


}
