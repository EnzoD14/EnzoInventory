package view.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.swing.JOptionPane;

import modelo.Activo;
import modelo.Solicitud;
import modelo.dao.impl.ActivoDAOimpl;
import modelo.dao.impl.SolicitudDAOimpl;
import view.screen.SolicitudBajaView;

public class SolicitudBajaEvents {
	
	private SolicitudBajaView solicitudBajaView;
	
	public SolicitudBajaEvents(SolicitudBajaView solicitudBajaView) {
		if (solicitudBajaView == null) {
			throw new IllegalArgumentException("solicitudBajaView cannot be null");
		}

		this.solicitudBajaView = solicitudBajaView;
		initEventHandlers();
	}
	
	private void initEventHandlers() {
		
		solicitudBajaView.setBtnBuscar(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscarActivo();
			}
		});

		solicitudBajaView.setBtnGuardar(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					guardarSolicitud();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		solicitudBajaView.setBtnCancelar(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Cancelar");
				solicitudBajaView.dispose(); // Cierra la ventana si el usuario cancela
			}
		});

	}
	
	private void guardarSolicitud() throws SQLException {
		System.out.println("Guardar");
		Activo activo = new Activo();
		ActivoDAOimpl activoDAO = new ActivoDAOimpl();
		Solicitud solicitud = new Solicitud();
		SolicitudDAOimpl solicitudDAO = new SolicitudDAOimpl();
		LocalDate fecha = LocalDate.now();
        Date fechaSql = Date.valueOf(fecha);
		
		activo = activoDAO.obtenerActivoPorNumeroSerie(solicitudBajaView.getBusqueda());
		
		solicitud.setActivo(activo);
		solicitud.setTipoSolicitud("Baja");
		solicitud.setFechaSolicitud(fechaSql);
		solicitud.setMotivoBaja(solicitudBajaView.getMotivoBaja());
		solicitud.setEstado(0);
		solicitud.setBaja(0);
		
		if (solicitudDAO.agregarSolicitud(solicitud)) {
			JOptionPane.showMessageDialog(null, "Solicitud de baja guardada con éxito.", "Información",
					JOptionPane.INFORMATION_MESSAGE);
			solicitudBajaView.dispose();
		} else {
			JOptionPane.showMessageDialog(null, "Error al guardar la solicitud de baja.", "Error",
					JOptionPane.ERROR_MESSAGE);
			solicitudBajaView.dispose();
		}
	}
	
	private void buscarActivo() {
		ActivoDAOimpl activoDAO = new ActivoDAOimpl();
		Activo activo = new Activo();
		String busqueda = solicitudBajaView.getBusqueda();
		
		if (busqueda.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Debe ingresar un código de activo.", "Error", JOptionPane.ERROR_MESSAGE);
		} else {
			System.out.println("Buscando activo");
			activo = activoDAO.obtenerActivoPorNumeroSerie(busqueda);
			if (activo == null) {
				JOptionPane.showMessageDialog(null, "Activo no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			} else {
				JOptionPane.showMessageDialog(null, "Activo encontrado.", "Información", JOptionPane.INFORMATION_MESSAGE);
				solicitudBajaView.setBusqueda(activo.getNumeroSerie());
				solicitudBajaView.setBusquedaEnabled(false);
				solicitudBajaView.setBtnGuardarEnabled(true);
				solicitudBajaView.setBtnBuscarEnabled(false);
			}
		}
	}

}
