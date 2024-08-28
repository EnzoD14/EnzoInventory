package view.screen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controlador.UsuarioController;
import view.events.LoginEvents;


public class LoginView {
    private UsuarioController usuarioLogin;
    private JFrame frame;
    private JTextField txtUsuario;
    private JPasswordField txtPassword;
    private JButton btnLogin;

    // Constructor
    public LoginView(UsuarioController usuario) {
        this.usuarioLogin = usuario;
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Login - Inventario Electronico");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setBounds(30, 30, 80, 25);
        frame.add(lblUsuario);

        txtUsuario = new JTextField();
        txtUsuario.setBounds(100, 30, 160, 25);
        frame.add(txtUsuario);

        JLabel lblPassword = new JLabel("Contraseña:");
        lblPassword.setBounds(30, 70, 80, 25);
        frame.add(lblPassword);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(100, 70, 160, 25);
        frame.add(txtPassword);

        btnLogin = new JButton("Ingresar");
        btnLogin.setBounds(100, 110, 160, 25);
        frame.add(btnLogin);

        // Se delegan los eventos a la clase LoginEvents
        LoginEvents loginEvents = new LoginEvents(usuarioLogin, this);
        btnLogin.addActionListener(loginEvents.getLoginButtonListener());
        txtPassword.addKeyListener(loginEvents.getEnterKeyListener());

        frame.setVisible(true);
    }

    public String getUsuario() {
    	return txtUsuario.getText();
    }
    
    public String getPassword() {
    	return new String(txtPassword.getPassword());
    }
    
    public JFrame getFrame() {
    	return frame;
    }
}
