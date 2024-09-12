package view.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controlador.ActivoController;
import controlador.CompraController;
import modelo.Compra;
import modelo.dao.impl.ActivoDAOimpl;
import modelo.dao.impl.CompraDAOimpl;
import view.screen.ActivoListaView;
import view.screen.ActivoAltaView;
import view.screen.CompraBusquedaView;
import view.screen.SolicitudAltaView;
import view.screen.ActivoGestionView;

public class ActivoGestionEvents {
	private CompraBusquedaView viewCompra;
	private ActivoGestionView view;
	private CompraDAOimpl compraDAO;
	private ActivoDAOimpl activoDAO;
	
	public ActivoGestionEvents (CompraBusquedaView viewCompra, ActivoGestionView view, CompraDAOimpl compra, ActivoDAOimpl activo) {
		this.viewCompra = viewCompra;
		this.view = view;
		this.compraDAO = new CompraDAOimpl();
		this.activoDAO = new ActivoDAOimpl();
		initEventHandlers();
	}

	public void initEventHandlers() {
		view.setControladorAlta(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agregarActivo();
			}
		});
		
		view.setControladorListar(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listarActivos();
			}
		});
		
		view.setControladorModificar(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//modificarActivo();
			}
		});
		
		view.setControladorBaja(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//bajaActivo();
			}
		});
		
		view.setControladorSolicitudAlta(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				solicitudAlta();
			}
		});
		
		view.setControladorSolicitudBaja(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//solicitudBaja();
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
	
	private void listarActivos() {
		System.out.println("listar");
		ActivoListaView activoListaView = new ActivoListaView();
		activoListaView.setVisible(true);
		
	}
	
	private void solicitudAlta() {
		System.out.println("solicitud alta");
		SolicitudAltaView solicitudAltaView = new SolicitudAltaView();
		new SolicitudAltaEvent(solicitudAltaView);
		solicitudAltaView.setVisible(true);
	}

}
