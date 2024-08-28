package controlador;

import modelo.Activo;
import modelo.dao.ActivoDAO;
import modelo.dao.impl.ActivoDAOimpl;

import java.sql.SQLException;
import java.util.List;

public class ActivoController {
	private ActivoDAO activoDAO;
	
	public ActivoController() {
		this.activoDAO = new ActivoDAOimpl();
	}
	
	public void agregarActivo(Activo activo) throws SQLException {
		activoDAO.agregarActivo(activo);
	}
	
	public void modificarActivo(Activo activo) {
		activoDAO.modificarActivo(activo);
	}
	
	public void eliminarActivo(Activo activo) {
		activoDAO.eliminarActico(activo);
	}
	
	public Activo obtenerActivoPorId(int id) {
		return activoDAO.obtenerActivoPorId(id);
	}
	
	public List<Activo> listarActivos(){
		return activoDAO.listarActivos();
	}
	
	public void imprimirActivo(Activo activo) {
		activoDAO.imprimirActivo(activo);
	}
}

