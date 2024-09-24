package modelo.dao;

import java.sql.SQLException;
import java.util.List;

import modelo.Activo;

public interface ActivoDAO {
	Boolean agregarActivo(Activo activo) throws SQLException;
	Boolean modificarActivo(Activo activo);
	void eliminarActico(Activo activo);
	Activo obtenerActivoPorId(int id);
	Activo obtenerActivoPorNumeroSerie(String numeroSerie);
	List<Activo> listarActivos(String numeroSerie);
	void imprimirActivo(Activo activo);
}
