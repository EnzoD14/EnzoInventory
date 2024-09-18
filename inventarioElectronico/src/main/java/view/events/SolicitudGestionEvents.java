package view.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import modelo.Activo;
import modelo.dao.impl.ActivoDAOimpl;
import modelo.dao.impl.SolicitudDAOimpl;
import view.screen.SolicitudAltaView;
import view.screen.SolicitudAprobarView;
import view.screen.SolicitudGestionView;

public class SolicitudGestionEvents {
	private SolicitudGestionView solicitudGestionView;
	private Activo activo;
	
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
				  aprobarSolicitud(); 
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
	
	private void aprobarSolicitud() {
		Activo activo2 = new Activo();
		System.out.println("aprobar");
		
		activo2 = solicitudGestionView.getSolicitudSeleccionada().getActivo();
		
		SolicitudAprobarView solicitudAprobarView = new SolicitudAprobarView();
		new SolicitudAprobarEvents(solicitudAprobarView, activo);
	}
	
	private void rechazarSolicitud() throws SQLException {
		Activo activo2 = new Activo();
		SolicitudDAOimpl solicitudDAO = new SolicitudDAOimpl();
		System.out.println(solicitudGestionView.getSolicitudSeleccionada().getTipoSolicitud());
		//Integer id = solicitudGestionView.getSolicitudSeleccionada().getTipoSolicitud();
		Integer id = 8;
		
		System.out.println("rechazar2");
		activo2 = solicitudDAO.obtenerActivoPorSolicitudId(id);
		System.out.println("rechazar3");
		System.out.println(activo2.getMarca() + activo2.getEspecificaciones());
	}
}
