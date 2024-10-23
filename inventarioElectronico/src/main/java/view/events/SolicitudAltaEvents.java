package view.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.swing.JOptionPane;

import modelo.Activo;
import modelo.Empleado;
import modelo.Solicitud;
import modelo.dao.impl.ActivoDAOimpl;
import modelo.dao.impl.SolicitudDAOimpl;
import view.screen.SolicitudAltaView;

public class SolicitudAltaEvents {
	
	private SolicitudAltaView solicitudAltaView;
	private ActivoDAOimpl activoDAO;
	
	public SolicitudAltaEvents(SolicitudAltaView solicitudAltaView) {
		if (solicitudAltaView == null) {
			throw new IllegalArgumentException("solicitudAltaView cannot be null");
		}

		this.solicitudAltaView = solicitudAltaView;
		this.activoDAO = new ActivoDAOimpl();
		initEventHandlers();
	}
	
	private void initEventHandlers() {
		
		solicitudAltaView.setBtnGuardar(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	if (validarCampos()) {
            		String nroSerie = solicitudAltaView.getNumeroSerie();
            		if (activoDAO.obtenerActivoPorNumeroSerie(nroSerie) != null) {
            			JOptionPane.showMessageDialog(null, "Ya existe un activo con ese número de serie.", "Error", JOptionPane.ERROR_MESSAGE);
            			return;
            		} else {
            			try {
        					guardarSolicitud();
        				} catch (SQLException e1) {
        					// TODO Auto-generated catch block
        					e1.printStackTrace();
        				}
            		}
            	}
            }
        });
		
		solicitudAltaView.setBtnCancelar(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	System.out.println("Cancelar");
                solicitudAltaView.dispose(); // Cierra la ventana si el usuario cancela
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
		
		activo.setTipo(solicitudAltaView.getTipoActivo());
		activo.setMarca(solicitudAltaView.getMarca());
		activo.setModelo(solicitudAltaView.getModelo());
		activo.setNumeroSerie(solicitudAltaView.getNumeroSerie());
		activo.setEspecificaciones(solicitudAltaView.getEspecificaciones());
		activo.setValor(solicitudAltaView.getValor());
		activo.setEmpleado((Empleado) solicitudAltaView.getCmbEmpleado().getSelectedItem());
		activo.setEstado(null);
		activo.setFechaAlta(fechaSql);
		activo.setFechaMantenimiento(null);
		activo.setBaja(1);
		
		activoDAO.agregarActivo(activo);
		
		solicitud.setActivo(activo);
		solicitud.setTipoSolicitud("Alta");
		solicitud.setFechaSolicitud(fechaSql);
		solicitud.setEstado(0);
		solicitud.setBaja(0);
		
		if (solicitudDAO.agregarSolicitud(solicitud)) {
			JOptionPane.showMessageDialog(null, "Solicitud de alta guardada con éxito.", "Información",
					JOptionPane.INFORMATION_MESSAGE);
			solicitudAltaView.dispose();
		} else {
			JOptionPane.showMessageDialog(null, "Error al guardar la solicitud de alta.", "Error",
					JOptionPane.ERROR_MESSAGE);
			solicitudAltaView.dispose();
		}
	}
	
	private boolean validarCampos() {
		if (solicitudAltaView.getTipoActivo().isEmpty() || solicitudAltaView.getMarca().isEmpty() || solicitudAltaView.getModelo().isEmpty() || solicitudAltaView.getNumeroSerie().isEmpty() || solicitudAltaView.getEspecificaciones().isEmpty() || solicitudAltaView.getValor().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
		
		try {
			Double.parseDouble(solicitudAltaView.getValor());
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "El campo 'Valor' debe ser numerico.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
        return true;
	}

}
