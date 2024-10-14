package view.screen;

import java.awt.Component;
import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import modelo.Usuario;
import view.events.LoginEvents;


public class LoginView {
    private Usuario usuarioLogin;
    private JFrame frame;
    private JTextField txtUsuario;
    private JPasswordField txtPassword;
    private JButton btnLogin;

    // Constructor
    public LoginView() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Login - Inventario Electronico");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BoxLayout boxLayout = new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS);
        frame.setLayout(boxLayout);

        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setAlignmentX(Component.CENTER_ALIGNMENT);
        frame.add(lblUsuario);
        frame.add(Box.createVerticalStrut(5));

        txtUsuario = new JTextField();
        txtUsuario.setPreferredSize(new Dimension(200, 30));
        txtUsuario.setMaximumSize(txtUsuario.getPreferredSize());
        txtUsuario.setAlignmentX(Component.CENTER_ALIGNMENT);
        frame.add(txtUsuario);
        frame.add(Box.createVerticalStrut(10));

        JLabel lblPassword = new JLabel("Contraseña:");
        lblPassword.setAlignmentX(Component.CENTER_ALIGNMENT);
        frame.add(lblPassword);
        frame.add(Box.createVerticalStrut(5));

        txtPassword = new JPasswordField();
        txtPassword.setPreferredSize(new Dimension(200, 30));
        txtPassword.setMaximumSize(txtPassword.getPreferredSize());
        txtPassword.setAlignmentX(Component.CENTER_ALIGNMENT);
        frame.add(txtPassword);

        frame.add(Box.createVerticalStrut(15));
        
        btnLogin = new JButton("Ingresar");
        btnLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
        frame.add(btnLogin);
        
        frame.add(Box.createVerticalGlue(), 0);
        frame.add(Box.createVerticalGlue());
        

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
