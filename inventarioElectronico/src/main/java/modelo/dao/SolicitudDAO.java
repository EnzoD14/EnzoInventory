package modelo.dao;

import java.sql.SQLException;

import modelo.Solicitud;

public interface SolicitudDAO {
	void agregarSolicitud(Solicitud solicitud) throws SQLException;
	void imprimirSolicitud(Solicitud solicitud);
}
