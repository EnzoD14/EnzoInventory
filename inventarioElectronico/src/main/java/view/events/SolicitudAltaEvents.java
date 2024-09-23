package view.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

import modelo.Activo;
import modelo.Solicitud;
import modelo.dao.impl.ActivoDAOimpl;
import modelo.dao.impl.SolicitudDAOimpl;
import view.screen.SolicitudAltaView;

public class SolicitudAltaEvents {
	
	private SolicitudAltaView solicitudAltaView;
	
	public SolicitudAltaEvents(SolicitudAltaView solicitudAltaView) {
		if (solicitudAltaView == null) {
			throw new IllegalArgumentException("solicitudAltaView cannot be null");
		}

		this.solicitudAltaView = solicitudAltaView;
		initEventHandlers();
	}
	
	private void initEventHandlers() {
		
		solicitudAltaView.setBtnGuardar(new ActionListener() {
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
		activo.setEstado(null);
		activo.setFechaAlta(fechaSql);
		activo.setFechaMantenimiento(null);
		activo.setBaja(1);
		
		activoDAO.agregarActivo(activo);
		
		solicitud.setActivo(activo);
		solicitud.setTipoSolicitud(solicitudAltaView.getTipoSolicitud());
		solicitud.setFechaSolicitud(fechaSql);
		solicitud.setEstado(0);
		solicitud.setBaja(0);
		
		if(solicitudDAO.agregarSolicitud(solicitud)) {
            System.out.println("Solicitud guardada");
        } else {
        	System.out.println("Error al guardar solicitud ");
        }
	}

}
