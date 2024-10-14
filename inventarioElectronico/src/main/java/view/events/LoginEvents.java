package view.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JOptionPane;

import controlador.UsuarioController;
import modelo.Usuario;
import view.screen.LoginView;
import view.screen.ModulosView;

public class LoginEvents {
	@SuppressWarnings("unused")
	private Usuario usuarioLogin;
	private LoginView loginView;
	
	public LoginEvents(Usuario usuarioLogin, LoginView loginView) {
		this.usuarioLogin = usuarioLogin;
		this.loginView = loginView;
	}
	
	public ActionListener getLoginButtonListener() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login();
			}
		};
	}
	
	public KeyAdapter getEnterKeyListener() {
		return new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    login();
                }
            }
        };
	}
	
	private void login() {
		String usuario = loginView.getUsuario();
		String password = loginView.getPassword();
		Usuario usuarioLog = new Usuario();
		UsuarioController usuarioLogin = new UsuarioController(usuarioLog);
		
		usuarioLog = usuarioLogin.iniciarSesion(usuario, password);
		
		if (usuarioLog != null) {
            JOptionPane.showMessageDialog(loginView.getFrame(), "Login exitoso");
            // Lógica para abrir la siguiente vista o pantalla
            loginView.getFrame().setVisible(false);
            @SuppressWarnings("unused")
			ModulosView moduloVista = new ModulosView(usuarioLog);
        } else {
            JOptionPane.showMessageDialog(loginView.getFrame(), "Credenciales incorrectas");
        }
	}
}
