package view.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import controlador.ActivoController;
import modelo.Activo;
import view.screen.AltaActivoView;

public class AltaActivoEvent {
	private AltaActivoView vista;
    private ActivoController controller;

    public AltaActivoEvent(AltaActivoView vista, ActivoController controller) {
        this.vista = vista;
        this.controller = controller;
        initEventHandlers();
    }

    private void initEventHandlers() {
        vista.setControladorGuardar(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
					guardarActivo();
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

    private void guardarActivo() throws SQLException {
        // Crear un nuevo objeto Activo con los datos de la vista
		// Llamar al controlador para guardar el nuevo activo );
        
        //controller.imprimirActivo(nuevoActivo);
        // Llamar al controlador para guardar el nuevo activo
        //controller.agregarActivo(nuevoActivo);

        // Cerrar la ventana después de guardar
        vista.dispose();
    }

}
