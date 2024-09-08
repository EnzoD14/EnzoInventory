package controlador;

import java.util.List;

import modelo.Compra;
import modelo.dao.CompraDAO;
import modelo.dao.impl.CompraDAOimpl;

public class CompraController {

	private CompraDAO compraDAO;
	
	public CompraController() {
		this.compraDAO = new CompraDAOimpl();
	}
	
	public void agregarCompra(Compra compra) {
		compraDAO.agregarCompra(compra);
	}
	
	public void modificarCompra(Compra compra) {
		compraDAO.modificarCompra(compra);
	}
	
	public void eliminarCompra(Compra compra) {
		compraDAO.eliminarCompra(compra);
	}
	
	public Compra buscarCompraPorId(String id) {
		return compraDAO.buscarCompraPorId(id);
	}
	
	public List<Compra> listarCompras() {
		return compraDAO.listarCompras();
	}
	
	public Compra buscarComprasPorNumeroFactura(String numeroFactura) {
		System.out.println("llego al controller");
		return compraDAO.buscarComprasPorNumeroFactura(numeroFactura);
	}

	public Compra getCompra() {
		return compraDAO.getCompra();
	}
	
}
