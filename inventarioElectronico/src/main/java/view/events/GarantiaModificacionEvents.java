package view.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modelo.Activo;
import modelo.Garantia;
import modelo.dao.impl.ActivoDAOimpl;
import modelo.dao.impl.GarantiaDAOimpl;
import view.screen.GarantiaModificacionView;
import view.screen.GarantiaView;

public class GarantiaModificacionEvents {
	
	private GarantiaModificacionView garantiaModificacionView;
	private GarantiaView garantiaView;
	
	public GarantiaModificacionEvents(GarantiaView garantiaView, GarantiaModificacionView garantiaModificacionView) {
		this.garantiaModificacionView = garantiaModificacionView;
		this.garantiaView = garantiaView;
		initEventHandlers();
	}
	
	private void initEventHandlers() {
		garantiaModificacionView.setModificarButtonActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Modificar Garantia");
				modificarGarantia();
			}
		});

		garantiaModificacionView.setCancelButtonActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Cancelar");
				garantiaModificacionView.dispose();
			}
		});
	}
	
	public void modificarGarantia() {
		Activo activo = new Activo();
		ActivoDAOimpl activoDAO = new ActivoDAOimpl();
		GarantiaDAOimpl garantiaDAO = new GarantiaDAOimpl();
		
		activo = activoDAO.obtenerActivoPorNumeroSerie(garantiaView.getNumeroSerie());
		
		java.util.Date utilDate = garantiaModificacionView.getFechaInicio().getDate();
		java.util.Date utilDate2 = garantiaModificacionView.getFechaFinal().getDate();

		java.sql.Date fechaInicio = new java.sql.Date(utilDate.getTime());
		java.sql.Date fechaFinal = new java.sql.Date(utilDate2.getTime());
		
		System.out.println(fechaInicio + " - " + fechaFinal);
		
		Garantia garantia = garantiaDAO.obtenerGarantiaPorIdActivo(activo.getId());

		garantia.setFechaInicio(fechaInicio);
		garantia.setFechaFin(fechaFinal);
		
		garantiaDAO.modificarGarantia(garantia);
	}
}
