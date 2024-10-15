package view.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.screen.ReparacionAltaView;

public class ReparacionAltaEvents {
	private ReparacionAltaView reparacionAltaView;
	
	public ReparacionAltaEvents(ReparacionAltaView reparacionAltaView) {
		this.reparacionAltaView = reparacionAltaView;
		initEventHandlers();
	}
	
	public void initEventHandlers() {
		reparacionAltaView.setGrabarReparacionButton(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("grabar reparacion");
				//grabarReparacion();
			}
		});
		
		reparacionAltaView.setCancelarReparacionButton(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("cancelar reparacion");
				reparacionAltaView.dispose();
			}
		});
		
		reparacionAltaView.setBuscarActivoButton(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("buscar activo");
				// buscarActivo();
			}
		});
	}
}
