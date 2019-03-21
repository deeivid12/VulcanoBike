package com.vulcanobike.app.business;

import java.util.ArrayList;
import java.util.List;

import com.vulcanobike.app.data.DataTipoProducto;
import com.vulcanobike.app.entities.Aplicacion;
import com.vulcanobike.app.entities.Marca;
import com.vulcanobike.app.entities.TipoProducto;

public class Controlador {
	
	private List<TipoProducto> catalogoTipoProducto = new ArrayList<TipoProducto>();
	private List<Marca> catalogoMarca = new ArrayList<Marca>();
	private List<Aplicacion> catalogoAplicacion = new ArrayList<Aplicacion>();
	
	private DataTipoProducto dataTipoProducto = new DataTipoProducto();

	//TIPOPRODUCTO
	public List<TipoProducto> getCatalogoTipoProducto() {
		return catalogoTipoProducto;
	}

	public void setCatalogoTipoProducto(List<TipoProducto> catalogoTipoProducto) {
		this.catalogoTipoProducto = catalogoTipoProducto;
	}
	
	/*public List<TipoProducto> getAllTipoProducto(){
		return catalogoTipoProducto;
	}*/
	
	public List<TipoProducto> getAllTipoProducto(){
		return dataTipoProducto.GetAll();
	}
	
	public void addTipoProducto(TipoProducto tProducto) {
		dataTipoProducto.Insert(tProducto);
		//catalogoTipoProducto.add(tProducto);
	}
	
	/*public TipoProducto findOneTipoProducto(String nombre) { //DESPUES CAMBIAR POR ID!!
		TipoProducto tProducto = null;
		for(TipoProducto tp : catalogoTipoProducto) {
			if(tp.getNombre().equals(nombre)) tProducto=tp;
		}
		return tProducto;
	}*/
	
	
	public TipoProducto findOneTipoProducto(Integer id) {
		TipoProducto tProducto = dataTipoProducto.GetOne(id);
		return tProducto;
	}
	
	/* public void updateTipoProducto(TipoProducto tipoProducto) {
		
		for(TipoProducto tp : catalogoTipoProducto) {
			if(tp.getNombre().equals(tipoProducto.getNombre())){
				tp.setNombre(tipoProducto.getNombre());
				tp.setDescripcion(tipoProducto.getDescripcion());
			}
		}
	}*/
	
	
	public void updateTipoProducto(TipoProducto tipoProducto) {
		dataTipoProducto.Update(tipoProducto);
	}
	
	public void deleteTipoProducto(int id) {//DESPUES CAMBIAR POR INT ID, PORQUE AHORA SE HACE POR NOMBRE!
		/*int indice=0;
		for(TipoProducto tp : catalogoTipoProducto) {
			if(tp.getNombre().equals(nombre)) break;
			else indice=indice+1;
		}
		catalogoTipoProducto.remove(indice);*/
		dataTipoProducto.Delete(id);
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
