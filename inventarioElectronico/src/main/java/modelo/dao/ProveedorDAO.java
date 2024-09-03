package modelo.dao;

import modelo.Proveedor;

public interface ProveedorDAO {
	Boolean agregarProveedor(Proveedor proveedor);
	Boolean modificarProveedor(Proveedor proveedor);
	void eliminarProveedor(Proveedor proveedor);
	void buscarProveedorPorRazonSocial(String razonSocial);
	Proveedor getIdProveedor();
	Proveedor listarProveedor();
}
