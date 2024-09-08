package modelo.dao;

import java.sql.SQLException;
import java.util.List;

import modelo.Activo;

public interface ActivoDAO {
	Boolean agregarActivo(Activo activo) throws SQLException;
	void modificarActivo(Activo activo);
	void eliminarActico(Activo activo);
	Activo obtenerActivoPorId(int id);
	List<Activo> listarActivos();
	void imprimirActivo(Activo activo);
}
