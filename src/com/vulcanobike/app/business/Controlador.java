package com.vulcanobike.app.business;

import java.util.ArrayList;
import java.util.List;

import com.vulcanobike.app.entities.Marca;
import com.vulcanobike.app.entities.TipoProducto;

public class Controlador {
	
	private List<TipoProducto> catalogoTipoProducto = new ArrayList<TipoProducto>();
	private List<Marca> catalogoMarca = new ArrayList<Marca>();


	public List<TipoProducto> getCatalogoTipoProducto() {
		return catalogoTipoProducto;
	}

	public void setCatalogoTipoProducto(List<TipoProducto> catalogoTipoProducto) {
		this.catalogoTipoProducto = catalogoTipoProducto;
	}
	
	public List<TipoProducto> getAllTipoProducto(){
		return catalogoTipoProducto;
	}
	
	public void addTipoProducto(TipoProducto tProducto) {
		catalogoTipoProducto.add(tProducto);
	}

	public List<Marca> getCatalogoMarca() {
		return catalogoMarca;
	}

	public void setCatalogoMarca(List<Marca> catalogoMarca) {
		this.catalogoMarca = catalogoMarca;
	}
	
	public List<Marca> getAllMarca(){
		return catalogoMarca;
	}
	
	public void addMarca(Marca marca) {
		catalogoMarca.add(marca);
	}

}
