package view.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import modelo.Activo;
import modelo.Solicitud;
import modelo.dao.impl.ActivoDAOimpl;
import modelo.dao.impl.SolicitudDAOimpl;
import view.screen.SolicitudAltaView;
import view.screen.SolicitudAprobarView;
import view.screen.SolicitudBajaView;
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
		
		  solicitudGestionView.setControladorSolicitudAlta(new ActionListener() {
			  public void actionPerformed(ActionEvent e) { 
			  altaSolicitud(); 
			  } });
		  
		  solicitudGestionView.setControladorSolicitudBaja(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				bajaSolicitud();
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
	
	private void altaSolicitud() {
		System.out.println("altaSC");
		SolicitudAltaView solicitudAltaView = new SolicitudAltaView();
		new SolicitudAltaEvents(solicitudAltaView);
	}
	
	private void bajaSolicitud() {
		System.out.println("bajaSC");
		SolicitudBajaView solicitudBajaView = new SolicitudBajaView();
		new SolicitudBajaEvents(solicitudBajaView);
	}
	
	private void aprobarSolicitud() throws SQLException {
		Activo activo2 = new Activo();
		ActivoDAOimpl activoDAO = new ActivoDAOimpl();
		SolicitudDAOimpl solicitudDAO = new SolicitudDAOimpl();
		Solicitud solicitud = new Solicitud();
		String busqueda = solicitudGestionView.getBusqueda();
		int idSolicitud = Integer.parseInt(busqueda);
		solicitud = solicitudDAO.obtenerSolicitudPorId(idSolicitud);
		System.out.println("aprobar");
		
		if (solicitud.getEstado() == 0) {
			if (solicitud.getTipoSolicitud().equals("Alta")) {
	            solicitud.setEstado(1);
				solicitudDAO.actualizarSolicitud(solicitud);
	            activo2 = solicitudDAO.obtenerActivoPorSolicitudId(idSolicitud);
	            System.out.println(activo2.getEspecificaciones());
	            SolicitudAprobarView solicitudAprobarView = new SolicitudAprobarView();
	    		new SolicitudAprobarEvents(solicitudAprobarView, activo2); 
			} else {
				solicitud.setEstado(1);
				solicitudDAO.actualizarSolicitud(solicitud);
				activo2 = solicitudDAO.obtenerActivoPorSolicitudId(idSolicitud);
				activo2.setBaja(1);
				activoDAO.modificarActivo(activo2);
				JOptionPane.showMessageDialog(null, "Solicitud de baja aprobada!");
			}
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
