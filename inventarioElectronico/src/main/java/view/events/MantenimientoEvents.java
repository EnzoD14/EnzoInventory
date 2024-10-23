package view.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.screen.MantenimientoView;
import view.screen.ReparacionAltaView;
import view.screen.ReparacionBajaView;

public class MantenimientoEvents {
	private MantenimientoView mantenimientoView;
	
	public MantenimientoEvents(MantenimientoView mantenimientoView) {
		this.mantenimientoView = mantenimientoView;
		initEventHandlers();
	}
	
	public void initEventHandlers() {
		mantenimientoView.setBtnAltaReparacionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("alta");
				ReparacionAltaView reparacionAltaView = new ReparacionAltaView();
				new ReparacionAltaEvents(reparacionAltaView);
			}
		});

		mantenimientoView.setBtnBajaReparacionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("baja");
				ReparacionBajaView reparacionBajaView = new ReparacionBajaView();
				new ReparacionBajaEvents(reparacionBajaView);
			}
		});

		mantenimientoView.setBtnMantenimientoListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("mantenimiento");
				//mantenimiento();
			}
		});
	}
	
}
