package com.vulcanobike.app.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Pedido {
	
	public static enum EstadosPedido{Pendiente, En_Proceso, Enviado};
	
	private int id;
	private String fechaEmision;
	private float importe;
	private Usuario usuario;
	private List<ItemPedido> items = new ArrayList<ItemPedido>();
	private EstadosPedido estado;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFechaEmision() {
		return fechaEmision;
	}
	public void setFechaEmision(String fechaEmision) {
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
	public EstadosPedido getEstado() {
		return estado;
	}
	public void setEstado(EstadosPedido estado) {
		this.estado = estado;
	}
		
	
}
