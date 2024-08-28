package controlador;

import java.sql.SQLException;

import modelo.Solicitud;
import modelo.dao.SolicitudDAO;
import modelo.dao.impl.SolicitudDAOimpl;

public class SolicitudController {
	private SolicitudDAO solicitudDAO;
	
	public SolicitudController() {
		this.solicitudDAO = new SolicitudDAOimpl();
	}
	
	public void agregarSolicitud(Solicitud solicitud) throws SQLException {
		solicitudDAO.agregarSolicitud(solicitud);
	}
	
	public void imprimirSolicitud(Solicitud solicitud) {
		solicitudDAO.imprimirSolicitud(solicitud);
	}
}
