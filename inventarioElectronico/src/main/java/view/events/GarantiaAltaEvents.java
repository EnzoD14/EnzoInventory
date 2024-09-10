package view.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modelo.Activo;
import modelo.Garantia;
import modelo.dao.impl.ActivoDAOimpl;
import modelo.dao.impl.GarantiaDAOimpl;
import view.screen.GarantiaAltaView;
import view.screen.GarantiaView;

public class GarantiaAltaEvents {
	private GarantiaAltaView garantiaAltaView;
	private GarantiaView garantiaView;
	
	public GarantiaAltaEvents(GarantiaView garantiaView, GarantiaAltaView garantiaAltaView) {
		this.garantiaAltaView = garantiaAltaView;
		this.garantiaView = garantiaView;
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
		
		activo = activoDAO.obtenerActivoPorNumeroSerie(garantiaView.getNumeroSerie());
		
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
