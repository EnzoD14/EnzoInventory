package view.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import controlador.ActivoController;
import modelo.Activo;
import modelo.Solicitud;
import utils.DateUtils;
import view.screen.AltaActivoView;

public class SolicitudAltaEvent {
	private AltaActivoView vista;
    private ActivoController controller;

    public SolicitudAltaEvent(AltaActivoView vista, ActivoController controller) {
        this.vista = vista;
        this.controller = controller;
        initEventHandlers();
    }

    private void initEventHandlers() {
        vista.setControladorGuardar(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
					solicitudAlta();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });

        vista.setControladorCancelar(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vista.dispose(); // Cierra la ventana si el usuario cancela
            }
        });
    }

    private void solicitudAlta() throws SQLException {
        // Crear un nuevo objeto Activo con los datos de la vista
        
        
        //Grabar solicitud en bdd

        // Cerrar la ventana después de guardar
        vista.dispose();
    }

}
