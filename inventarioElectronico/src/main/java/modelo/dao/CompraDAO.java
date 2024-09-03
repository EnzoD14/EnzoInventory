package modelo.dao;

import java.util.Date;
import java.util.List;

import modelo.Compra;
import modelo.Proveedor;

public interface CompraDAO {
	
	public Boolean agregarCompra(Compra compra);
    
    public Boolean modificarCompra(Compra compra);
    
    public Boolean eliminarCompra(Compra compra);
    
    public Compra buscarCompraPorId(String id);
    
    public List<Compra> listarCompras();
    
    public List<Compra> buscarComprasPorProveedor(Proveedor proveedor);
    
    public List<Compra> buscarComprasPorFecha(Date fecha);
    
    public List<Compra> buscarComprasPorNumeroFactura(String numeroFactura);
    
    public List<Compra> buscarComprasPorProveedorYFecha(Proveedor proveedor, Date fecha);
    
    public List<Compra> buscarComprasPorProveedorYNumeroFactura(Proveedor proveedor, String numeroFactura);
    
    public List<Compra> buscarComprasPorFechaYNumeroFactura(Date fecha, String numeroFactura);
    
    public List<Compra> buscarComprasPorProveedorFechaYNumeroFactura(Proveedor proveedor, Date fecha, String numeroFactura);
    
    public List<Compra> buscarComprasPorProveedorYFechaYNumeroFactura(Proveedor proveedor, Date fecha, String numeroFactura);
}
