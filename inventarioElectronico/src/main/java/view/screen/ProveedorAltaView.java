package view.screen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import controlador.UsuarioController;

public class ProveedorAltaView {
	private UsuarioController usuarioLogin;
    private JFrame frame;
    private JTextField nombreField;
    private JTextField razonSocialField;
    private JTextField emailField;
    private JTextField telefonoField;
    private JButton aceptarButton;
    private JButton cancelarButton;

    public ProveedorAltaView(UsuarioController usuarioLogin) {
        frame = new JFrame("Alta de Proveedor - Inventario Electronico");
        frame.setSize(300, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        
        JMenuBar menuBar = new JMenuBar();
        JMenu menuArchivo = new JMenu("Archivo");
        
        JMenuItem itemModulos = new JMenuItem("Modulos");
        itemModulos.addActionListener(new ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                ModulosView moduloVista = new ModulosView(usuarioLogin);
            }
        });
        
        JMenuItem itemSalir = new JMenuItem("Salir");
        itemSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        JMenuItem itemCerrarSesion = new JMenuItem("Cerrar Sesion");
        itemCerrarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                LoginView loginView = new LoginView(usuarioLogin);
            }
        });

        nombreField = new JTextField();
        razonSocialField = new JTextField();
        emailField = new JTextField();
        telefonoField = new JTextField();

        frame.add(new JLabel("Nombre:"));
        frame.add(nombreField);
        frame.add(new JLabel("Razón Social:"));
        frame.add(razonSocialField);
        frame.add(new JLabel("Email:"));
        frame.add(emailField);
        frame.add(new JLabel("Teléfono:"));
        frame.add(telefonoField);
        
        aceptarButton = new JButton("Aceptar");
        cancelarButton = new JButton("Cancelar");
        

        frame.add(aceptarButton);
        frame.add(cancelarButton);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    // Getters para los campos de texto
    public String getNombre() {
        return nombreField.getText();
    }

    public String getRazonSocial() {
        return razonSocialField.getText();
    }

    public String getEmail() {
        return emailField.getText();
    }

    public String getTelefono() {
        return telefonoField.getText();
    }
    
	public void setAceptar(ActionListener listener) {
		aceptarButton.addActionListener(listener);
	}
	
	public void setCancelar(ActionListener listener) {
		cancelarButton.addActionListener(listener);
	}

	public void setVisible(boolean visible) {
		frame.setVisible(visible);
	}

}