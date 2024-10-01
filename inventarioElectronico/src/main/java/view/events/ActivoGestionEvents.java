package view.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.dao.impl.ActivoDAOimpl;
import modelo.dao.impl.CompraDAOimpl;
import view.screen.ActivoAltaView;
import view.screen.CompraBusquedaView;
import view.screen.GarantiaModificacionView;
import view.screen.SolicitudGestionView;
import view.screen.ActivoGestionView;

public class ActivoGestionEvents {
	private ActivoGestionView activoView;
	private CompraDAOimpl compraDAO;
	private ActivoDAOimpl activoDAO;
	
	public ActivoGestionEvents (CompraBusquedaView viewCompra, ActivoGestionView activoView, CompraDAOimpl compra, ActivoDAOimpl activo) {
		this.activoView = activoView;
		this.compraDAO = new CompraDAOimpl();
		this.activoDAO = new ActivoDAOimpl();
		initEventHandlers();
	}

	public void initEventHandlers() {
		activoView.setControladorSolicitud(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				solicitud();
			}
		});
		
		activoView.setControladorActivoModificar(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agregarActivo();
			}
		});
		
		activoView.setControladorGarantiaModificar(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificarGarantia();
			}
		});
	}
	
	private void agregarActivo() {
		System.out.println("agregar");
		ActivoAltaView altaView = new ActivoAltaView();
		CompraBusquedaView compraView = null;
		new AltaActivoEvent(compraView, altaView, compraDAO, activoDAO);
		altaView.setVisible(true);
	}
	
	private void modificarGarantia() {
        System.out.println("modificar garantia");
        GarantiaModificacionView garantiaAltaView = new GarantiaModificacionView();
		new GarantiaModificacionEvents(activoView, garantiaAltaView);
	}
	
	private void solicitud() {
		System.out.println("solicitud");
		activoView.dispose();
		SolicitudGestionView solicitudView = new SolicitudGestionView();
		new SolicitudGestionEvents(solicitudView);
	}

}
