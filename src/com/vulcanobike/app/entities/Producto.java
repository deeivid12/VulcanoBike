package com.vulcanobike.app.entities;

public class Producto {
	
	private int id;
	private String nombre;
	private String descripcion;
	private double precio;
	private int stock;
	private TipoProducto tipoProducto;
	private Marca marca;
	private Rodado rodado;
	private AplicacionBicicleta aplicacionBicicleta;
	
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
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
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
	public AplicacionBicicleta getAplicacionBicicleta() {
		return aplicacionBicicleta;
	}
	public void setAplicacionBicicleta(AplicacionBicicleta aplicacionBicicleta) {
		this.aplicacionBicicleta = aplicacionBicicleta;
	}
	


}
