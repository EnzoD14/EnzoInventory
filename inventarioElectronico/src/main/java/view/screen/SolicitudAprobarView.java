package view.screen;

import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class SolicitudAprobarView extends JFrame{
	private JFrame frame;
	private JTextField txtAmortizacion;
	private JTextField txtPatrimonio;
	private JButton btnGuardar;
	private JButton btnCancelar;
	
	public SolicitudAprobarView() {
		initialize();
	}
	
	private void initialize() {
		frame = new JFrame("Aprobacion Solicitud - Inventario Electronico");
        frame.setSize(400, 600);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        BoxLayout boxLayout = new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS);
        frame.setLayout(boxLayout);
        
        frame.add(Box.createVerticalStrut(15));
        
        JLabel lblAmortizacion = new JLabel("Ingresar meses a amortizar:");
        lblAmortizacion.setAlignmentX(0.5f);
        frame.add(lblAmortizacion);
        
        txtAmortizacion = new JTextField();
        txtAmortizacion.setAlignmentX(0.5f);
        txtAmortizacion.setPreferredSize(new java.awt.Dimension(200, 30));
        txtAmortizacion.setMaximumSize(txtAmortizacion.getPreferredSize());
        frame.add(txtAmortizacion);
        frame.add(Box.createVerticalStrut(5));
        
        JLabel lblPatrimonio = new JLabel("Ingresar numero de patrimonio:");
        lblPatrimonio.setAlignmentX(0.5f);
        frame.add(lblPatrimonio);
        
        txtPatrimonio = new JTextField();
        txtPatrimonio.setAlignmentX(0.5f);
        txtPatrimonio.setPreferredSize(new java.awt.Dimension(200, 30));
        txtPatrimonio.setMaximumSize(txtPatrimonio.getPreferredSize());
        frame.add(txtPatrimonio);
        frame.add(Box.createVerticalStrut(15));
        
        btnGuardar = new JButton("Guardar");
        btnGuardar.setAlignmentX(0.5f);
        frame.add(btnGuardar);
        frame.add(Box.createVerticalStrut(5));
        
        btnCancelar = new JButton("Cancelar");
        btnCancelar.setAlignmentX(0.5f);
        frame.add(btnCancelar);
        frame.add(Box.createVerticalStrut(5));
        
        frame.setVisible(true);
	}
	
	public String getTxtAmortizacion() {
		return txtAmortizacion.getText();
	}
	
	public String getTxtPatrimonio() {
		return txtPatrimonio.getText();
	}
	
	public void setBtnGuardar(ActionListener actionListener) {
		btnGuardar.addActionListener(actionListener);
	}
	
	public void setBtnCancelar(ActionListener actionListener) {
		btnCancelar.addActionListener(actionListener);
	}
}
