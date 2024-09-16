package modelo.dao;

import java.sql.SQLException;
import java.util.List;

import modelo.Solicitud;

public interface SolicitudDAO {
	void agregarSolicitud(Solicitud solicitud) throws SQLException;
	void imprimirSolicitud(Solicitud solicitud);
	List<Solicitud> listarSolicitudes(String tipoSolicitud);
}
