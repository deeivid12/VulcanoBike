package com.vulcanobike.app.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Pedido {
	
	private int id;
	private Date fechaEmision;
	private float importe;
	private Usuario usuario;
	private List<ItemPedido> items = new ArrayList<ItemPedido>();
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getFechaEmision() {
		return fechaEmision;
	}
	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}
	public float getImporte() {
		return importe;
	}
	public void setImporte(float importe) {
		this.importe = importe;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public List<ItemPedido> getItems() {
		return items;
	}
	public void setItems(List<ItemPedido> items) {
		this.items = items;
	}
	
	
	
	
}
