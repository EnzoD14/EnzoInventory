package view.screen;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

@SuppressWarnings("serial")
public class ReparacionAltaView extends JFrame{
	private JFrame frame;
	private JDateChooser fechaReparacionChooser;
	private JTextField activoField;
	private JTextField motivoField;
	private JTextField costoField;
	private JButton grabarReparacionButton;
	private JButton cancelarReparacionButton;
	private JButton buscarActivo;
	
	public ReparacionAltaView() {
		frame = new JFrame("Alta de Reparacion - Inventario Electronico");
		frame.setSize(500, 500);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		BoxLayout boxLayout = new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS);
		frame.getContentPane().setLayout(boxLayout);
		
		frame.add(Box.createVerticalStrut(15));
		
		JLabel lblFechaReparacion = new JLabel("Fecha de la reparacion:");
		lblFechaReparacion.setAlignmentX(0.5f);
		frame.add(lblFechaReparacion);
		
		fechaReparacionChooser = new JDateChooser();
		fechaReparacionChooser.setAlignmentX(0.5f);
		fechaReparacionChooser.setPreferredSize(new Dimension(200, 30));
		fechaReparacionChooser.setMaximumSize(fechaReparacionChooser.getPreferredSize());
		frame.add(fechaReparacionChooser);
		
		frame.add(Box.createVerticalStrut(5));
		
		JLabel lblActivoReparacion = new JLabel("Activo a reparar:");
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
		
		JLabel lblMotivoReparacion = new JLabel("Motivo de la reparacion:");
		lblMotivoReparacion.setAlignmentX(0.5f);
		frame.add(lblMotivoReparacion);
		
		motivoField = new JTextField();
		motivoField.setAlignmentX(0.5f);
		motivoField.setPreferredSize(new Dimension(200, 30));
		motivoField.setMaximumSize(motivoField.getPreferredSize());
		frame.add(motivoField);
		
		frame.add(Box.createVerticalStrut(5));
		
		JLabel lblCostoReparacion = new JLabel("Costo de la reparacion:");
		lblCostoReparacion.setAlignmentX(0.5f);
		frame.add(lblCostoReparacion);
		
		costoField = new JTextField();
		costoField.setAlignmentX(0.5f);
		costoField.setPreferredSize(new Dimension(200, 30));
		costoField.setMaximumSize(costoField.getPreferredSize());
		frame.add(costoField);
		
		frame.add(Box.createVerticalStrut(10));
		
		grabarReparacionButton = new JButton("Grabar Reparacion");
		grabarReparacionButton.setAlignmentX(0.5f);
		grabarReparacionButton.setEnabled(false);
		frame.add(grabarReparacionButton);
		
		frame.add(Box.createVerticalStrut(5));
		
		cancelarReparacionButton = new JButton("Cancelar Reparacion");
		cancelarReparacionButton.setAlignmentX(0.5f);
		frame.add(cancelarReparacionButton);

		frame.add(Box.createVerticalStrut(5));
		
		frame.setVisible(true);
	}
	
	public void setEnabledGrabarReparacionButton(boolean enabled) {
		grabarReparacionButton.setEnabled(enabled);
	}
	
	public void setEnabledBuscarActivoButton(boolean enabled) {
		buscarActivo.setEnabled(enabled);
	}
	
	public void setEnabledActivoField(boolean enabled) {
		activoField.setEnabled(enabled);
	}
	
	public JDateChooser getFechaReparacionChooser() {
		return fechaReparacionChooser;
	}
	
	public String getActivoField() {
		return activoField.getText();
	}
	
	public JTextField getMotivoField() {
		return motivoField;
	}
	
	public JTextField getCostoField() {
		return costoField;
	}
	
	public void setActivoField(String activo) {
		activoField.setText(activo);
	}
	
	public JButton getGrabarReparacionButton() {
		return grabarReparacionButton;
	}
	
	public JButton getCancelarReparacionButton() {
		return cancelarReparacionButton;
	}
	
	public void setGrabarReparacionButton(ActionListener listener) {
		grabarReparacionButton.addActionListener(listener);
	}
	
	public void setCancelarReparacionButton(ActionListener listener) {
		cancelarReparacionButton.addActionListener(listener);
	}
	
	public void setBuscarActivoButton(ActionListener listener) {
		buscarActivo.addActionListener(listener);
	}
	
	public void setVisible(boolean visible) {
		super.setVisible(visible);
	}

	public String getMotivoReparacion() {
		// TODO Auto-generated method stub
		return motivoField.getText();
	}

	public String getValorReparacion() {
		// TODO Auto-generated method stub
		return costoField.getText();
	}

}
