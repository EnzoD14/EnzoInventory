package view.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controlador.UsuarioController;
import view.screen.CompraView;
import view.screen.ProveedorAltaView;
import view.screen.ProveedorBajaView;

public class CompraEvents {
	private CompraView view;
	private UsuarioController usuarioLogin;
	
	public CompraEvents(CompraView view) {
		this.view = view;
		view.setVisible(true);
		initEventHandlers();
	}


	private void initEventHandlers() {
		// TODO Auto-generated method stub
		view.setControladorAlta(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agregarProveedor();
			}
		});
		
		view.setControladorBaja(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bajaProveedor();
			}
		});
		
		view.setControladorModificar(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificarProveedor();
			}
		});
		
		view.setControladorListar(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listarProveedor();
			}
		});
		
		view.setControladorSolicitudAlta(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                agregarSolicitud();
            }
        });
	}
	
	private void agregarProveedor() {
		ProveedorAltaView proveedorAltaView = new ProveedorAltaView(usuarioLogin);
		new ProveedorAltaEvents(proveedorAltaView);
		proveedorAltaView.setVisible(true);
		
	}
	
	private void bajaProveedor() {
		ProveedorBajaView proveedorBajaView = new ProveedorBajaView();
		new ProveedorBajaEvent(proveedorBajaView);
		proveedorBajaView.setVisible(true);
	}
	
	private void modificarProveedor() {
		System.out.println("modificarProveedor");
	}
	
	private void listarProveedor() {
		System.out.println("listarProveedor");
	}
	
	private void agregarSolicitud() {
		System.out.println("agregarSolicitud");
	}
}
