package view.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import modelo.Activo;
import modelo.Solicitud;
import modelo.dao.impl.SolicitudDAOimpl;
import view.screen.SolicitudAltaView;
import view.screen.SolicitudAprobarView;
import view.screen.SolicitudGestionView;

public class SolicitudGestionEvents {
	private SolicitudGestionView solicitudGestionView;
	public SolicitudGestionEvents(SolicitudGestionView solicitudGestionView) {
		if (solicitudGestionView == null) {
            throw new IllegalArgumentException("solicitudGestionView cannot be null");
        }
		this.solicitudGestionView = solicitudGestionView;
		initEventHandlers();
	}
	
	private void initEventHandlers() {	
		
		  solicitudGestionView.setControladorSolicitudCargar(new ActionListener() {
			  public void actionPerformed(ActionEvent e) { 
			  cargarSolicitud(); 
			  } });
		  
		  solicitudGestionView.setControladorSolicitudAprobar(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				  try {
					aprobarSolicitud();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
			  } });
			  
		  solicitudGestionView.setControladorSolicitudRechazar(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				  try {
					rechazarSolicitud();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
			} });
			 
		 
	}
	
	private void cargarSolicitud() {
		System.out.println("cargar");
		SolicitudAltaView solicitudAltaView = new SolicitudAltaView();
		new SolicitudAltaEvents(solicitudAltaView);
	}
	
	private void aprobarSolicitud() throws SQLException {
		Activo activo2 = new Activo();
		SolicitudDAOimpl solicitudDAO = new SolicitudDAOimpl();
		Solicitud solicitud = new Solicitud();
		String busqueda = solicitudGestionView.getBusqueda();
		int idSolicitud = Integer.parseInt(busqueda);
		solicitud = solicitudDAO.obtenerSolicitudPorId(idSolicitud);
		System.out.println("aprobar");
		
		if (solicitud.getEstado() == 0) {
            solicitud.setEstado(1);
			solicitudDAO.actualizarSolicitud(solicitud);
            activo2 = solicitudDAO.obtenerActivoPorSolicitudId(idSolicitud);
            System.out.println(activo2.getEspecificaciones());
            SolicitudAprobarView solicitudAprobarView = new SolicitudAprobarView();
    		new SolicitudAprobarEvents(solicitudAprobarView, activo2); 
        } else {
            JOptionPane.showMessageDialog(null, "La solicitud no se puede aprobar");
        }
		
		
	}
	
	private void rechazarSolicitud() throws SQLException {
		
		SolicitudDAOimpl solicitudDAO = new SolicitudDAOimpl();
		Solicitud solicitud = new Solicitud();
		String busqueda = solicitudGestionView.getBusqueda();
		int idSolicitud = Integer.parseInt(busqueda);
		solicitud = solicitudDAO.obtenerSolicitudPorId(idSolicitud);
		
		if (solicitud.getEstado() == 0) {
			solicitud.setEstado(2);
			solicitudDAO.actualizarSolicitud(solicitud);
			System.out.println("Solicitud Rechazada");
		} else {
			JOptionPane.showMessageDialog(null, "La solicitud no se puede rechazar");
		}
	}
}
