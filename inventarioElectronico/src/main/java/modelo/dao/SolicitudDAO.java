package modelo.dao;

import java.sql.SQLException;
import java.util.List;

import modelo.Activo;
import modelo.Solicitud;

public interface SolicitudDAO {
	Boolean agregarSolicitud(Solicitud solicitud) throws SQLException;
	void imprimirSolicitud(Solicitud solicitud);
	List<Solicitud> listarSolicitudes(String tipoSolicitud);
	Activo obtenerActivoPorSolicitudId(int id) throws SQLException;
	Solicitud obtenerSolicitudPorId(int id) throws SQLException;
	Solicitud obtenerSolicitudPorIdActivo(String id, String tipo) throws SQLException;
	Boolean actualizarSolicitud(Solicitud solicitud) throws SQLException;
}
