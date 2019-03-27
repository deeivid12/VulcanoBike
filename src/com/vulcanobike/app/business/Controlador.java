package com.vulcanobike.app.business;

import java.util.ArrayList;
import java.util.List;

import com.vulcanobike.app.data.DataAplicacion;
import com.vulcanobike.app.data.DataItemPedido;
import com.vulcanobike.app.data.DataPedido;
import com.vulcanobike.app.data.DataProducto;
import com.vulcanobike.app.data.DataRodado;
import com.vulcanobike.app.data.DataTipoProducto;
import com.vulcanobike.app.data.DataUsuario;
import com.vulcanobike.app.entities.Aplicacion;
import com.vulcanobike.app.entities.ItemPedido;
import com.vulcanobike.app.entities.Marca;
import com.vulcanobike.app.entities.Pedido;
import com.vulcanobike.app.entities.Producto;
import com.vulcanobike.app.entities.Rodado;
import com.vulcanobike.app.entities.TipoProducto;
import com.vulcanobike.app.entities.Usuario;

public class Controlador {
	
	private List<TipoProducto> catalogoTipoProducto = new ArrayList<TipoProducto>();
	private List<Marca> catalogoMarca = new ArrayList<Marca>();
	private List<Aplicacion> catalogoAplicacion = new ArrayList<Aplicacion>();
	
	private DataTipoProducto dataTipoProducto = new DataTipoProducto();
	private DataProducto dataProducto = new DataProducto();
	private DataPedido dataPedido = new DataPedido();
	private DataItemPedido dataItemPedido = new DataItemPedido();
	private DataUsuario dataUsuario = new DataUsuario();
	private DataAplicacion dataAplicacion = new DataAplicacion();
	private DataRodado dataRodado = new DataRodado();

	
	//LOGIN
	public Usuario login(String user, String pass) {
		return dataUsuario.login(user, pass);
	}
	
	public void registro(Usuario usuario) {
		dataUsuario.Insert(usuario);
	}
	
	public Usuario getOneUsuarioByUser(String user) {//para buscar por correo o por user
		return dataUsuario.GetOneByUser(user);
	}
	
	public Usuario getOneUsuarioByEmail(String email) {//para buscar por correo o por user
		return dataUsuario.GetOneByEmail(email);
	}

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
	
	public List<TipoProducto> getAllTipoProducto() throws Exception{
		return dataTipoProducto.GetAll();
	}
	
	public void addTipoProducto(TipoProducto tProducto) throws Exception{
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
	
	
	public TipoProducto findOneTipoProducto(Integer id) throws Exception{
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
	
	
	public void updateTipoProducto(TipoProducto tipoProducto) throws Exception{
		dataTipoProducto.Update(tipoProducto);
	}
	
	public void deleteTipoProducto(int id) throws Exception{
	
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
	
	public void addAplicacion(Aplicacion aplicacion) throws Exception {
		dataAplicacion.Insert(aplicacion);
	}
	
	public List<Aplicacion> getAllAplicacion() throws Exception{
		return dataAplicacion.GetAll();
	}
	
	public Aplicacion getOneAplicacion(int id) throws Exception {
		return dataAplicacion.GetOne(id);
	}
	
	
	public void updateAplicacion(Aplicacion aplicacion) throws Exception {
		dataAplicacion.Update(aplicacion);
	}
	
	public void deleteAplicacion(int id) throws Exception {
		dataAplicacion.Delete(id);
	}
	
	
	//PRODUCTO
	public List<Producto> getAllProducto(){

		return  dataProducto.GetAll();
	}
	
	
	public Producto getOneProducto(Integer id) {
		Producto Producto = dataProducto.GetOne(id);
		return Producto;
	}
	
	public void updateProducto(Producto producto) {
		dataProducto.Update(producto);
	}
	
	
	//PEDIDO
	public void addPedido(Pedido pedido) {
		int idPedido = dataPedido.Insert(pedido); //guardo pedido y obtengo id autogenerado
		addItemPedido(pedido.getItems(), idPedido); //guardo items con el id de pedido correspondiente
	}
	
	public List<Pedido> getAllPedido() throws Exception {
		List<Pedido> pedidos = dataPedido.GetAll();
		//for(Pedido p:pedidos) {
			//Usuario u = dataUsuario.GetOneById(p.getUsuario().getId());
			//List<ItemPedido> items = dataItemPedido.GetAllByIdPedido(p.getId());
			//p.setUsuario(u);
			//p.setItems(items);
		//	}
		return pedidos;
	}
	
	public float calcularImportePedido(List<ItemPedido> items) {
		float importePedido = 0;
		for(ItemPedido ip : items) { //calculo importe de pedido!
			importePedido = importePedido + ip.getImporte();
		}
		return importePedido;
	}
	
	public String generadorMensaje(Pedido pedido) {

		String cuerpo = "Resumen de su compra: \n\n";
		for(ItemPedido ip:pedido.getItems()) {

			String lineaProducto = ip.getProducto().getNombre() + " x" + ip.getCantidad() + " = $" + ip.getImporte() + "\n";
			cuerpo = cuerpo + lineaProducto;
		}
		
		cuerpo = cuerpo + "Total = $" + pedido.getImporte();
		return cuerpo;
	}
	
	
	//ITEMPEDIDO
	public void addItemPedido(List<ItemPedido> itemsPedido, int idPedido) {
		
		for(ItemPedido ip : itemsPedido) {
			ip.setIdPedido(idPedido);
			dataItemPedido.Insert(ip);
		}
	}
	

	
	//RODADO
	
	public List<Rodado> getAllRodado() throws Exception{
		return dataRodado.GetAll();
	}
}
