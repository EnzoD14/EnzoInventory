package view.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.screen.SolicitudAltaView;
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
		  
		  solicitudGestionView.setControladorSolicitud(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				  agregarSolicitud(); 
			  } });
			  
		  solicitudGestionView.setControladorSolicitud2(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				  modificarSolicitud(); 
			} });
			 
		 
	}
	
	private void cargarSolicitud() {
		System.out.println("cargar");
		SolicitudAltaView solicitudAltaView = new SolicitudAltaView();
		new SolicitudAltaEvents(solicitudAltaView);
	}
	
	private void agregarSolicitud() {
		System.out.println("aprobar");
	}
	
	private void modificarSolicitud() {
		System.out.println("rechazar");
	}
}
