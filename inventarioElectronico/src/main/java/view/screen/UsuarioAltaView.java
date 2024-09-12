package view.screen;

import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import view.events.UsuarioAltaEvents;

@SuppressWarnings("serial")
public class UsuarioAltaView extends JFrame {
	private JFrame frame;
	private JTextField txtUsuarioAd;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtEmail;
	private JButton btnGuardar;
	private JButton btnCancelar;
	private JComboBox<String> comboTipo;

	public UsuarioAltaView() {
		initialize();
	}
	
	private void initialize() {
		frame = new JFrame("Alta de Usuario - Inventario Electronico");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        BoxLayout boxLayout = new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS);
        frame.setLayout(boxLayout);
        
        frame.add(Box.createVerticalStrut(15));
        
        JLabel lblUsuarioAd = new JLabel("Usuario Active Directory:");
        lblUsuarioAd.setAlignmentX(0.5f);
        frame.add(lblUsuarioAd);
        
        txtUsuarioAd = new JTextField();
        txtUsuarioAd.setAlignmentX(0.5f);
        txtUsuarioAd.setPreferredSize(new Dimension(200, 30));
        txtUsuarioAd.setMaximumSize(txtUsuarioAd.getPreferredSize());
        frame.add(txtUsuarioAd);
        frame.add(Box.createVerticalStrut(5));
        
        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setAlignmentX(0.5f);
        frame.add(lblNombre);
        
        txtNombre = new JTextField();
        txtNombre.setAlignmentX(0.5f);
        txtNombre.setPreferredSize(new Dimension(200, 30));
        txtNombre.setMaximumSize(txtNombre.getPreferredSize());
        frame.add(txtNombre);
        frame.add(Box.createVerticalStrut(5));
        
        JLabel lblApellido = new JLabel("Apellido:");
        lblApellido.setAlignmentX(0.5f);
        frame.add(lblApellido);
        
        txtApellido = new JTextField();
        txtApellido.setAlignmentX(0.5f);
        txtApellido.setPreferredSize(new Dimension(200, 30));
        txtApellido.setMaximumSize(txtApellido.getPreferredSize());
        frame.add(txtApellido);
        frame.add(Box.createVerticalStrut(5));
        
        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setAlignmentX(0.5f);
        frame.add(lblEmail);
        
        txtEmail = new JTextField();
        txtEmail.setAlignmentX(0.5f);
        txtEmail.setPreferredSize(new Dimension(200, 30));
        txtEmail.setMaximumSize(txtEmail.getPreferredSize());
        frame.add(txtEmail);
        frame.add(Box.createVerticalStrut(5));
        
        JLabel lblTipo = new JLabel("Tipo de usuario:");
        lblTipo.setAlignmentX(0.5f);
        frame.add(lblTipo);
        
        comboTipo = new JComboBox<>(new String[] {"Administrador", "It", "Finanzas", "Compras", "Ventas", "RRHH", "Marketing"});
        comboTipo.setAlignmentX(0.5f);
        comboTipo.setPreferredSize(new Dimension(200, 30));
        comboTipo.setMaximumSize(comboTipo.getPreferredSize());
        frame.add(comboTipo);
        frame.add(Box.createVerticalStrut(10));
        
        
        btnGuardar = new JButton("Guardar");
        btnGuardar.setAlignmentX(0.5f);
        frame.add(btnGuardar);
        
        frame.add(Box.createVerticalStrut(5));
        
        btnCancelar = new JButton("Cancelar");
        btnCancelar.setAlignmentX(0.5f);
        frame.add(btnCancelar);
        
        frame.setVisible(true);
	}
	
	public void dispose() {
		frame.dispose();
	}
	
	public String getUsuarioAd() {
		return txtUsuarioAd.getText();
	}
	
	public String getNombre() {
		return txtNombre.getText();
	}
	
	public String getApellido() {
		return txtApellido.getText();
	}
	
	public String getEmail() {
		return txtEmail.getText();
	}
	
	public JComboBox<String> getTipo() {
		return comboTipo;
	}
	
	public void setBtnGuardar(ActionListener actionListener) {
		btnGuardar.addActionListener(actionListener);
	}
	
	public void setBtnCancelar(ActionListener actionListener) {
		btnCancelar.addActionListener(actionListener);
	}
}
