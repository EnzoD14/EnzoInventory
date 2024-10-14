package view.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.Usuario;
import view.screen.CompraAltaView;
import view.screen.CompraView;
import view.screen.ProveedorAltaView;
import view.screen.ProveedorBajaView;
import view.screen.ProveedorListaView;
import view.screen.ProveedorModificacionBusquedaView;

public class CompraEvents {
	private CompraView view;
	private Usuario usuarioLogin;
	
	public CompraEvents(CompraView view) {
		this.view = view;
		view.setVisible(true);
		initEventHandlers();
	}


	private void initEventHandlers() {
		// TODO Auto-generated method stub
		view.setControladorAlta(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				view.deshabilitarBotones();
				agregarProveedor();
			}
		});
		
		view.setControladorBaja(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				view.deshabilitarBotones();
				bajaProveedor();
			}
		});
		
		view.setControladorModificar(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				view.deshabilitarBotones();
				modificarProveedor();
			}
		});
		
		view.setControladorSolicitudAlta(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	view.deshabilitarBotones();
                agregarSolicitud();
            }
        });
		
	}
	
	private void agregarProveedor() {
		ProveedorAltaView proveedorAltaView = new ProveedorAltaView(usuarioLogin);
		new ProveedorAltaEvents(proveedorAltaView, view);
		proveedorAltaView.setVisible(true);
	}
	
	private void bajaProveedor() {
		ProveedorBajaView proveedorBajaView = new ProveedorBajaView();
		new ProveedorBajaEvent(proveedorBajaView);
		proveedorBajaView.setVisible(true);
	}
	
	private void modificarProveedor() {
		ProveedorModificacionBusquedaView proveedorModificacionBuscar = new ProveedorModificacionBusquedaView();
		//ProveedorModificacionView proveedorModificacionView = new ProveedorModificacionView();
		new ProveedorModificacionBusquedaEvent(proveedorModificacionBuscar);
		proveedorModificacionBuscar.setVisible(true);
	}
	
	@SuppressWarnings("unused")
	private void listarProveedor() {
		ProveedorListaView proveedorListaView = new ProveedorListaView(usuarioLogin);
		proveedorListaView.setVisible(true);
	}
	
	private void agregarSolicitud() {
		System.out.println("agregarSolicitud");
		CompraAltaView compraView = new CompraAltaView();
		new CompraAltaEvents(compraView);
	}
}
