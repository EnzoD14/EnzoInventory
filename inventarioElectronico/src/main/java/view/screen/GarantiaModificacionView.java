package view.screen;

import javax.swing.*;

import com.toedter.calendar.JDateChooser;

import java.awt.*;
import java.awt.event.ActionListener;

public class GarantiaModificacionView extends JFrame {
    private JDateChooser fechaInicioChooser;
    private JDateChooser fechaFinalChooser;
    private JButton modificarButton;
    private JButton cancelButton;

    public GarantiaModificacionView() {
        initialize();
    }

    private void initialize() {
        setLayout(new FlowLayout());

        modificarButton = new JButton("Agregar");
        cancelButton = new JButton("Cancelar");

        add(new JLabel("Fecha Inicio:"));
        fechaInicioChooser = new JDateChooser();
        add(fechaInicioChooser);
        add(new JLabel("Fecha Final:"));
        fechaFinalChooser = new JDateChooser();
        add(fechaFinalChooser);
        add(modificarButton);
        add(cancelButton);

        pack();
        setVisible(true);
    }

	public JDateChooser getFechaInicio() {
		return fechaInicioChooser;
	}
	
	public JDateChooser getFechaFinal() {
		return fechaFinalChooser;
	}


    public void setModificarButtonActionListener(ActionListener listener) {
        modificarButton.addActionListener(listener);
    }

    public void setCancelButtonActionListener(ActionListener listener) {
        cancelButton.addActionListener(listener);
    }
}
