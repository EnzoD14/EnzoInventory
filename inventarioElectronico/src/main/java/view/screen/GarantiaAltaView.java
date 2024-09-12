package view.screen;

import javax.swing.*;

import com.toedter.calendar.JDateChooser;

import java.awt.*;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class GarantiaAltaView extends JFrame {
    private JDateChooser fechaInicioChooser;
    private JDateChooser fechaFinalChooser;
    private JButton agregarButton;
    private JButton cancelButton;

    public GarantiaAltaView() {
        initialize();
    }

    private void initialize() {
        setLayout(new FlowLayout());

        agregarButton = new JButton("Agregar");
        cancelButton = new JButton("Cancelar");

        add(new JLabel("Fecha Inicio:"));
        fechaInicioChooser = new JDateChooser();
        add(fechaInicioChooser);
        add(new JLabel("Fecha Final:"));
        fechaFinalChooser = new JDateChooser();
        add(fechaFinalChooser);
        add(agregarButton);
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


    public void setAgregarButtonActionListener(ActionListener listener) {
        agregarButton.addActionListener(listener);
    }

    public void setCancelButtonActionListener(ActionListener listener) {
        cancelButton.addActionListener(listener);
    }
}
