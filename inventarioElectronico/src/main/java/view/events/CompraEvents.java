package view.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.screen.CompraView;

public class CompraEvents {
	private CompraView view;
	
	
	public CompraEvents(CompraView view) {
		this.view = view;
		view.setVisible(true);
		initEventHandlers();
	}


	private void initEventHandlers() {
		// TODO Auto-generated method stub
		view.setControladorAlta(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agregarCompra();
			}
		});
		
		view.setControladorBaja(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bajaCompra();
			}
		});
		
		view.setControladorModificar(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificarCompra();
			}
		});
		
		view.setControladorListar(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listarCompra();
			}
		});
		
		view.setControladorSolicitudAlta(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                agregarSolicitud();
            }
        });
	}
	
	private void agregarCompra() {
		System.out.println("agregarCompra");
	}
	
	private void bajaCompra() {
		System.out.println("bajaCompra");
	}
	
	private void modificarCompra() {
		System.out.println("modificarCompra");
	}
	
	private void listarCompra() {
		System.out.println("listarCompra");
	}
	
	private void agregarSolicitud() {
		System.out.println("agregarSolicitud");
	}
}
