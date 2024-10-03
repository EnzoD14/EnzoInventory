package view.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.screen.MantenimientoView;

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
				//altaReparacion();
			}
		});

		mantenimientoView.setBtnBajaReparacionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("baja");
				//bajaReparacion();
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
