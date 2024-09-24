package view.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import modelo.Activo;
import modelo.dao.impl.ActivoDAOimpl;
import view.screen.SolicitudAprobarView;

public class SolicitudAprobarEvents {
	
	private SolicitudAprobarView solicitudAprobarView;
	private Activo activo;
	
	public SolicitudAprobarEvents(SolicitudAprobarView solicitudAprobarView, Activo activo) {
		if (solicitudAprobarView == null) {
			throw new IllegalArgumentException("solicitudAprobarView cannot be null");
		}

		this.solicitudAprobarView = solicitudAprobarView;
		this.activo = activo;
		initEventHandlers();
	}
	
	private void initEventHandlers() {

		solicitudAprobarView.setBtnGuardar(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					aprobarSolicitud(activo);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		solicitudAprobarView.setBtnCancelar(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				solicitudAprobarView.dispose(); // Cierra la ventana si el usuario cancela
			}
		});

	}
	
	private void aprobarSolicitud(Activo activo) throws SQLException {
		ActivoDAOimpl activoDAO = new ActivoDAOimpl();
		System.out.println("aprobar");
		
		Integer amortizacion = Integer.parseInt(solicitudAprobarView.getTxtAmortizacion());
		String patrimonio = solicitudAprobarView.getTxtPatrimonio();
		
		activo.setMesesAmortizacion(amortizacion);
		activo.setCodigoProducto(patrimonio);
		activo.setBaja(0);
		
		if (activoDAO.modificarActivo(activo)) {
			System.out.println("Activo agregado");
		} else {
			System.out.println("Error al agregar activo");
		}
	}

}
