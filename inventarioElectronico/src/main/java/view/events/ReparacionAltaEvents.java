package view.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import modelo.Activo;
import modelo.Reparacion;
import modelo.dao.impl.ActivoDAOimpl;
import modelo.dao.impl.ReparacionDAOimpl;
import view.screen.ReparacionAltaView;

public class ReparacionAltaEvents {
	private ReparacionAltaView reparacionAltaView;
	private Activo activo;
	
	public ReparacionAltaEvents(ReparacionAltaView reparacionAltaView) {
		this.reparacionAltaView = reparacionAltaView;
		this.activo = new Activo();
		initEventHandlers();
	}
	
	public void initEventHandlers() {
		ActivoDAOimpl activoDAO = new ActivoDAOimpl();
		
		reparacionAltaView.setGrabarReparacionButton(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("grabar reparacion");
				
				if (activo != null) {
					Reparacion reparacion = new Reparacion();
					ReparacionDAOimpl reparacionDAO = new ReparacionDAOimpl();
				    java.util.Date utilDate = reparacionAltaView.getFechaReparacionChooser().getDate();
				    java.sql.Date fechaReparacion = new java.sql.Date(utilDate.getTime());
					
					reparacion.setFechaInicio(fechaReparacion);
					reparacion.setMotivoReparacion(reparacionAltaView.getMotivoReparacion());
					reparacion.setEnReparacion(true);
					reparacion.setValor(reparacionAltaView.getValorReparacion());
					
					reparacionDAO.agregarReparacion(reparacion);
					
					activo.setEstado("Reparacion");
					activo.setReparacion(reparacion);
					activoDAO.modificarActivo(activo);
					System.out.println("Activo modificado");
					JOptionPane.showMessageDialog(reparacionAltaView, "Activo modificado", "Exito",
							JOptionPane.INFORMATION_MESSAGE);
				}
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
				String nroSerie = reparacionAltaView.getActivoField();
				
				activo = activoDAO.obtenerActivoPorNumeroSerie(nroSerie);
				
				System.out.println(nroSerie);
				
				if (activo != null) {
					reparacionAltaView.setActivoField(activo.getNumeroSerie().toString());
					reparacionAltaView.setEnabledGrabarReparacionButton(true);
					reparacionAltaView.setEnabledBuscarActivoButton(false);
					reparacionAltaView.setEnabledActivoField(false);
					System.out.println("Activo encontrado");
					JOptionPane.showMessageDialog(reparacionAltaView, "Activo encontrado", "Exito", JOptionPane.INFORMATION_MESSAGE);
				} else {
					System.out.println("Activo no encontrado");
					JOptionPane.showMessageDialog(reparacionAltaView, "Activo no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}
	
	
}
