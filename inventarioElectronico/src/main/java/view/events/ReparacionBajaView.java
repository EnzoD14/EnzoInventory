package view.events;

import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ReparacionBajaView extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3780769488630210201L;
	private JFrame frame;
	private JTextField activoField;
	private JButton buscarActivo;
	private JButton darBajaReparacion;
	private JButton cancelarBajaReparacion;

	public ReparacionBajaView() {
		frame = new JFrame("Baja de Reparacion - Inventario Electronico");
		frame.setSize(500, 220);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		BoxLayout boxLayout = new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS);
		frame.getContentPane().setLayout(boxLayout);
		
		frame.add(Box.createVerticalStrut(15));
		
		JLabel lblActivoReparacion = new JLabel("Activo reparado:");
		lblActivoReparacion.setAlignmentX(0.5f);
		frame.add(lblActivoReparacion);
		
		activoField = new JTextField();
		activoField.setAlignmentX(0.5f);
		activoField.setPreferredSize(new Dimension(200, 30));
		activoField.setMaximumSize(activoField.getPreferredSize());
		frame.add(activoField);
		
		frame.add(Box.createVerticalStrut(5));
		
		buscarActivo = new JButton("Buscar Activo");
		buscarActivo.setAlignmentX(0.5f);
		frame.add(buscarActivo);
		
		frame.add(Box.createVerticalStrut(10));
		
		darBajaReparacion = new JButton("Dar de baja reparacion");
		darBajaReparacion.setAlignmentX(0.5f);
		darBajaReparacion.setEnabled(false);
		frame.add(darBajaReparacion);
		
		frame.add(Box.createVerticalStrut(10));
		
		cancelarBajaReparacion = new JButton("Cancelar");
		cancelarBajaReparacion.setAlignmentX(0.5f);
		frame.add(cancelarBajaReparacion);
		
		frame.setVisible(true);
	}
	
	public JTextField getActivoField() {
		return activoField;
	}
	
	public JButton getBuscarActivoButton() {
		return buscarActivo;
	}
	
	public JButton getDarBajaReparacionButton() {
		return darBajaReparacion;
	}
	
	public JButton getCancelarBajaReparacionButton() {
		return cancelarBajaReparacion;
	}
	
	public void setEnabledDarBajaReparacionButton(boolean enabled) {
		darBajaReparacion.setEnabled(enabled);
	}
	
	public void setEnabledBuscarActivoButton(boolean enabled) {
        buscarActivo.setEnabled(enabled);
    }
	
	public void setEnabledActivoField(boolean enabled) {
		activoField.setEnabled(enabled);
	}
	
	public void setActivoField(String activo) {
		activoField.setText(activo);
	}
	
	public void setDarBajaReparacionButton(ActionListener action) {
		darBajaReparacion.addActionListener(action);
	}
	
	public void setCancelarBajaReparacionButton(ActionListener action) {
		cancelarBajaReparacion.addActionListener(action);
	}
	
	public void setBuscarActivoButton(ActionListener action) {
		buscarActivo.addActionListener(action);
	}
}
