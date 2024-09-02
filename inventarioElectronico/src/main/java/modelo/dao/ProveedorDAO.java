package modelo.dao;

import modelo.Proveedor;

public interface ProveedorDAO {
	Boolean agregarProveedor(Proveedor proveedor);
	void modificarProveedor(Proveedor proveedor);
	void eliminarProveedor(Proveedor proveedor);
	void buscarProveedorPorRazonSocial(String razonSocial);
	Proveedor listarProveedor();
}
