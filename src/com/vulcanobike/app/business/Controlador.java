package com.vulcanobike.app.business;

import java.util.ArrayList;
import java.util.List;

import com.vulcanobike.app.entities.Aplicacion;
import com.vulcanobike.app.entities.Marca;
import com.vulcanobike.app.entities.TipoProducto;

public class Controlador {
	
	private List<TipoProducto> catalogoTipoProducto = new ArrayList<TipoProducto>();
	private List<Marca> catalogoMarca = new ArrayList<Marca>();
	private List<Aplicacion> catalogoAplicacion = new ArrayList<Aplicacion>();

	//TIPOPRODUCTO
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
	
	public TipoProducto findOneTipoProducto(String nombre) { //DESPUES CAMBIAR POR ID!!
		TipoProducto tProducto = null;
		for(TipoProducto tp : catalogoTipoProducto) {
			if(tp.getNombre().equals(nombre)) tProducto=tp;
		}
		return tProducto;
	}
	
	public void updateTipoProducto(TipoProducto tipoProducto) {
		
		for(TipoProducto tp : catalogoTipoProducto) {
			if(tp.getNombre().equals(tipoProducto.getNombre())){
				tp.setNombre(tipoProducto.getNombre());
				tp.setDescripcion(tipoProducto.getDescripcion());
			}
		}
	}
	
	public void deleteTipoProducto(String nombre) {//DESPUES CAMBIAR POR INT ID, PORQUE AHORA SE HACE POR NOMBRE!
		int indice=0;
		for(TipoProducto tp : catalogoTipoProducto) {
			if(tp.getNombre().equals(nombre)) break;
			else indice=indice+1;
		}
		catalogoTipoProducto.remove(indice);
	}

	
	//MARCA
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
	
	
	//APLICACION
	public List<Aplicacion> getCatalogoAplicacion() {
		return catalogoAplicacion;
	}

	public void setCatalogoAplicacion(List<Aplicacion> catalogoAplicacion) {
		this.catalogoAplicacion = catalogoAplicacion;
	}
	
	public List<Aplicacion> getAllAplicacion(){
		return catalogoAplicacion;
	}
	
	public void addAplicacion(Aplicacion aplicacion) {
		catalogoAplicacion.add(aplicacion);
	}

}
