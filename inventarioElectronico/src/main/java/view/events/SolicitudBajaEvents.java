package view.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

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
	}

}
