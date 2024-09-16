package view.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.Activo;
import modelo.Garantia;
import modelo.dao.impl.ActivoDAOimpl;
import modelo.dao.impl.GarantiaDAOimpl;
import view.screen.ActivoGestionView;
import view.screen.GarantiaModificacionView;

public class GarantiaModificacionEvents {
	private GarantiaModificacionView garantiaAltaView;
	private ActivoGestionView activoView;
	
	public GarantiaModificacionEvents(ActivoGestionView activoView, GarantiaModificacionView garantiaAltaView) {
		this.garantiaAltaView = garantiaAltaView;
		this.activoView = activoView;
		initEventHandlers();
	}
	
	private void initEventHandlers() {
		garantiaAltaView.setAgregarButtonActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Grabar Garantia");
                grabarGarantia();
            }
        });

		garantiaAltaView.setCancelButtonActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Cancelar");
				garantiaAltaView.dispose();
			}
		});
	}
	
	public void grabarGarantia() {
		Activo activo = new Activo();
		ActivoDAOimpl activoDAO = new ActivoDAOimpl();
		
		activo = activoDAO.obtenerActivoPorNumeroSerie(activoView.getNumeroSerie());
		
		System.out.println("Activo: " + activo.getNumeroSerie() + " - " + activo.getMarca());
		
		Garantia garantia = new Garantia();
		GarantiaDAOimpl garantiaDAO = new GarantiaDAOimpl();
		
		java.util.Date utilDate = garantiaAltaView.getFechaInicio().getDate();
		java.util.Date utilDate2 = garantiaAltaView.getFechaFinal().getDate();

		java.sql.Date fechaInicio = new java.sql.Date(utilDate.getTime());
		java.sql.Date fechaFinal = new java.sql.Date(utilDate2.getTime());

		garantia.setFechaInicio(fechaInicio);
		garantia.setFechaFin(fechaFinal);

		System.out.println("Fecha Inicio: " + garantia.getFechaInicio());
		System.out.println("Fecha Final: " + garantia.getFechaFin());
		
		activo.setGarantia(garantia);
		
		if (garantiaDAO.agregarGarantia(garantia)) {
			System.out.println("Garantia guardada con exito bdd");
			activoDAO.modificarActivo(activo);
			System.out.println("Activo modificado con exito bdd");
			garantiaAltaView.dispose();
		} else {
			System.out.println("Error al guardar garantia en bdd");
			garantiaAltaView.dispose();
		}
		
	}
}
