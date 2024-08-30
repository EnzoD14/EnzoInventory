package modelo.dao;

import modelo.Proveedor;

public interface ProveedorDAO {
	void agregarProveedor(Proveedor proveedor);
	void modificarProveedor(Proveedor proveedor);
	void eliminarProveedor(Proveedor proveedor);
	Proveedor listarProveedor();
}
