package view.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import modelo.Activo;
import modelo.dao.impl.ActivoDAOimpl;
import view.screen.GarantiaAltaView;
import view.screen.GarantiaModificacionView;
import view.screen.GarantiaView;

public class GarantiaEvents {
	private  GarantiaView garantiaView;
	
	public GarantiaEvents(GarantiaView garantiaView) {
		this.garantiaView = garantiaView;
		initEventHandlers();
	}

	private void initEventHandlers() {
		garantiaView.setAgregarButton(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Agregar Garantia");
				GarantiaAltaView garantiaAltaView = new GarantiaAltaView();
				new GarantiaAltaEvents(garantiaView, garantiaAltaView);
			}
		});
		
		garantiaView.setModificarButton(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Modificar Garantia");
				GarantiaModificacionView garantiaModificacionView = new GarantiaModificacionView();
				new GarantiaModificacionEvents(garantiaView, garantiaModificacionView);
			}
		});
	}
	
	
	
}
