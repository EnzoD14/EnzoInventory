package view.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controlador.ActivoController;
import view.screen.AltaActivoView;
import view.screen.GestionActivosView;

public class GestionActivosEvents {
	private GestionActivosView view;
	private ActivoController activoController;
	
	public GestionActivosEvents (GestionActivosView view, ActivoController activoController) {
		this.view = view;
		this.activoController = activoController;
		initEventHandlers();
	}
	
	public void initEventHandlers() {
		view.setControladorAlta(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agregarActivo();
			}
		});
	}
	
	private void agregarActivo() {
		System.out.println("ola");
		AltaActivoView altaView = new AltaActivoView();
		new AltaActivoEvent(altaView, activoController);
		altaView.setVisible(true);
	}

}
