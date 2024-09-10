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
import view.screen.GestionActivosView;

public class GestionActivosEvents {
	private CompraBusquedaView viewCompra;
	private GestionActivosView view;
	private CompraDAOimpl compraDAO;
	private ActivoDAOimpl activoDAO;
	
	public GestionActivosEvents (CompraBusquedaView viewCompra, GestionActivosView view, CompraDAOimpl compra, ActivoDAOimpl activo) {
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

}
