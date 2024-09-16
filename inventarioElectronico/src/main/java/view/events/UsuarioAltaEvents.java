package view.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import modelo.Usuario;
import modelo.dao.impl.UsuarioDAOimpl;
import view.screen.UsuarioAltaView;
import view.screen.UsuarioGestionView;

public class UsuarioAltaEvents {
	
	private UsuarioAltaView usuarioAltaView;
	private UsuarioGestionView usuarioGestionView;

	public UsuarioAltaEvents(UsuarioAltaView usuarioAltaView, UsuarioGestionView usuarioGestionView) {
		this.usuarioAltaView = usuarioAltaView;
		this.usuarioGestionView = usuarioGestionView;
		initEventHandlers();
	}
	
	private void initEventHandlers() {
		usuarioAltaView.setBtnGuardar(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				guardarUsuario();
				actualizarTabla();
			}
		});

		usuarioAltaView.setBtnCancelar(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Cancelar Usuario");
				usuarioAltaView.dispose();
				actualizarTabla();
			}
		});
	}
	
	public void guardarUsuario() {
		
		Usuario usuario = new Usuario();
		UsuarioDAOimpl usuarioDAO = new UsuarioDAOimpl();
		
		usuario.setUsuarioAd(usuarioAltaView.getUsuarioAd());
		usuario.setNombre(usuarioAltaView.getNombre());
		usuario.setApellido(usuarioAltaView.getApellido());
		usuario.setEmail(usuarioAltaView.getEmail());
		usuario.setTipoUsuario(usuarioAltaView.getTipo().getSelectedItem().toString());
		usuario.setContrasena("123456");
		usuario.setBaja(0);
		
		if (usuarioDAO.agregarUsuario(usuario)) {
			System.out.println("Usuario agregado");
			JOptionPane.showMessageDialog(null, "Usuario guardado con exito!", "Alta Usuario", JOptionPane.INFORMATION_MESSAGE);
            usuarioAltaView.dispose();
        } else {
        	JOptionPane.showMessageDialog(null, "Error al guardar usuario.", "Error", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	private void actualizarTabla() {
		try {
			usuarioGestionView.setBusquedaTxtField("");
			usuarioGestionView.actualizarTabla("");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
}
