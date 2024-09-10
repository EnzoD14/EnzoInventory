package modelo.dao;

import modelo.Garantia;

public interface GarantiaDAO {

	Boolean agregarGarantia(Garantia garantia);
	Boolean modificarGarantia(Garantia garantia);
	void eliminarGarantia(Garantia garantia);
	void buscarGarantiaPorId(int id);
	Garantia obtenerGarantiaPorIdActivo(String id);
}
