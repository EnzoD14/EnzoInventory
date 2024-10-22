package view.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import modelo.Activo;
import modelo.Reparacion;
import modelo.dao.impl.ActivoDAOimpl;
import modelo.dao.impl.ReparacionDAOimpl;

public class ReparacionBajaEvents {
	private ReparacionBajaView reparacionBajaView;
	private Activo activo;
	private Reparacion reparacion;
	
	public ReparacionBajaEvents(ReparacionBajaView reparacionBajaView) {
		this.reparacionBajaView = reparacionBajaView;
		this.activo = new Activo();
		this.reparacion = new Reparacion();
		initEventHandlers();
	}
	
	public void initEventHandlers() {
		ActivoDAOimpl activoDAO = new ActivoDAOimpl();
		ReparacionDAOimpl reparacionDAO = new ReparacionDAOimpl();
		
		reparacionBajaView.setBuscarActivoButton(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("buscar activo");
				
				String nroSerie = reparacionBajaView.getActivoField().getText();
				activo = activoDAO.obtenerActivoPorNumeroSerie(nroSerie);
				
				if (activo != null) {
					System.out.println("Activo encontrado: " + activo.getNumeroSerie());
					reparacionBajaView.getActivoField().setText(activo.getNumeroSerie());
					reparacionBajaView.setEnabledDarBajaReparacionButton(true);
					reparacionBajaView.setEnabledBuscarActivoButton(false);
					reparacionBajaView.setEnabledActivoField(false);
				} else {
					JOptionPane.showMessageDialog(reparacionBajaView, "Activo no encontrado.", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		reparacionBajaView.setDarBajaReparacionButton(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("dar de baja reparacion");
				
				if (activo != null) {
					reparacion = activo.getReparacion();
					reparacion.setEnReparacion(false);
					reparacionDAO.modificarReparacion(reparacion);
					activo.setEstado(null);
					activo.setReparacion(null);
					activoDAO.modificarActivo(activo);
					
					System.out.println("Activo modificado");
					JOptionPane.showMessageDialog(reparacionBajaView, "Reparacion dada de baja.", "Exito",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});

		reparacionBajaView.setCancelarBajaReparacionButton(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("cancelar baja reparacion");
				reparacionBajaView.dispose();
			}
		});
	}
}
