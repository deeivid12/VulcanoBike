package com.vulcanobike.app.business;

import java.util.ArrayList;
import java.util.List;

import com.vulcanobike.app.data.DataAplicacion;
import com.vulcanobike.app.data.DataItemPedido;
import com.vulcanobike.app.data.DataMarca;
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
	
	private DataTipoProducto dataTipoProducto = new DataTipoProducto();
	private DataProducto dataProducto = new DataProducto();
	private DataPedido dataPedido = new DataPedido();
	private DataItemPedido dataItemPedido = new DataItemPedido();
	private DataUsuario dataUsuario = new DataUsuario();
	private DataAplicacion dataAplicacion = new DataAplicacion();
	private DataRodado dataRodado = new DataRodado();
	private DataMarca dataMarca = new DataMarca();

	
	//LOGIN
	public Usuario login(String user, String pass) throws Exception {
		return dataUsuario.login(user, pass);
	}
	
	public void registro(Usuario usuario) throws Exception {
		dataUsuario.Insert(usuario);
	}
	
	public Usuario getOneUsuarioByUser(String user) throws Exception {//para buscar por correo o por user
		return dataUsuario.GetOneByUser(user);
	}
	
	public Usuario getOneUsuarioByEmail(String email) throws Exception {//para buscar por correo o por user
		return dataUsuario.GetOneByEmail(email);
	}
	
	
	//USUARIO
	
	public List<Usuario> getAllUsuario() throws Exception{
		return dataUsuario.GetAll();
	}
	
	public Usuario getOneUsuario(int id) throws Exception {
		return dataUsuario.GetOne(id);
	}
	
	public void deleteUsuario(int id) throws Exception {
		dataUsuario.Delete(id);
	}
	
	public void updateUsuario(Usuario usuario) throws Exception {
		dataUsuario.Update(usuario);
	}
	

	//TIPOPRODUCTO
	
	public List<TipoProducto> getAllTipoProducto() throws Exception{
		return dataTipoProducto.GetAll();
	}
	
	public void addTipoProducto(TipoProducto tProducto) throws Exception{
		dataTipoProducto.Insert(tProducto);
	}
	
	
	
	public TipoProducto findOneTipoProducto(Integer id) throws Exception{
		TipoProducto tProducto = dataTipoProducto.GetOne(id);
		return tProducto;
	}
	
	
	public void updateTipoProducto(TipoProducto tipoProducto) throws Exception{
		dataTipoProducto.Update(tipoProducto);
	}
	
	public void deleteTipoProducto(int id) throws Exception{
	
		dataTipoProducto.Delete(id);
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
	public List<Producto> getAllProducto() throws Exception{

		return  dataProducto.GetAll();
	}
	
	
	public Producto getOneProducto(Integer id) throws Exception {
		Producto Producto = dataProducto.GetOne(id);
		return Producto;
	}
	
	public void updateProducto(Producto producto) throws Exception {
		dataProducto.Update(producto);
	}
	
	public void addProducto(Producto producto) throws Exception {
		dataProducto.Insert(producto);
	}
	
	public void deleteProducto(int id) throws Exception {
		dataProducto.Delete(id);
	}
	
	
	//PEDIDO
	public void addPedido(Pedido pedido) throws Exception {
		int idPedido = dataPedido.Insert(pedido); //guardo pedido y obtengo id autogenerado
		addItemPedido(pedido.getItems(), idPedido); //guardo items con el id de pedido correspondiente
	}
	
	public List<Pedido> getAllPedido() throws Exception {
		List<Pedido> pedidos = dataPedido.GetAll();
		return pedidos;
	}
	
	public List<Pedido> getAllPedidoByIdUsuario(int idUsuario) throws Exception{
		return dataPedido.GetAll(idUsuario);
	}
	
	public void updatePedido(Pedido pedido) throws Exception {
		dataPedido.Update(pedido);
	}
	
	public Pedido getOnePedido(int id) throws Exception {
		
		Pedido pedido = dataPedido.GetOne(id);
		List<ItemPedido> items = getAllItemPedidoById(id);
		pedido.setItems(items);
		 
		
		return pedido;
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
	public void addItemPedido(List<ItemPedido> itemsPedido, int idPedido) throws Exception {
		
		for(ItemPedido ip : itemsPedido) {
			ip.setIdPedido(idPedido);
			dataItemPedido.Insert(ip);
		}
	}
	
	
	public List<ItemPedido> getAllItemPedidoById(int id) throws Exception{
		return dataItemPedido.GetAll(id);
	}
	

	
	//RODADO
	
	public List<Rodado> getAllRodado() throws Exception{
		return dataRodado.GetAll();
	}
	
	public void addRodado(Rodado rodado) throws Exception {
		dataRodado.Insert(rodado);
	}
	
	
	public Rodado getOneRodado(int id) throws Exception {
		return dataRodado.GetOne(id);
	}
	
	
	public void updateRodado(Rodado rodado) throws Exception {
		dataRodado.Update(rodado);
	}
	
	public void deleteRodado(int id) throws Exception {
		dataRodado.Delete(id);
	}
	
	
	//MARCA
	public List<Marca> getAllMarca() throws Exception{
		return dataMarca.GetAll();
	}
	
	public void addMarca(Marca marca) throws Exception {
		dataMarca.Insert(marca);
	}
	
	
	public Marca getOneMarca(int id) throws Exception {
		return dataMarca.GetOne(id);
	}
	
	
	public void updateMarca(Marca marca) throws Exception {
		dataMarca.Update(marca);
	}
	
	public void deleteMarca(int id) throws Exception {
		dataMarca.Delete(id);
	}
	
}
