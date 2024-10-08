package view.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import modelo.Usuario;
import modelo.dao.impl.UsuarioDAOimpl;
import view.screen.UsuarioGestionView;
import view.screen.UsuarioModificacionView;

public class UsuarioModificacionEvents {
	
	private UsuarioModificacionView usuarioModificacionView;
	private UsuarioGestionView usuarioGestionView;
	private Usuario usuario;
	
	public UsuarioModificacionEvents(UsuarioModificacionView usuarioModificacionView, UsuarioGestionView usuarioGestionView, Usuario usuario) {
		this.usuarioModificacionView = usuarioModificacionView;
		this.usuarioGestionView = usuarioGestionView;
		this.usuario = usuario;
		initEventHandlers();
	}
	
	private void initEventHandlers() {
		
		usuarioModificacionView.setBtnGuardar(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Modificar Usuario");
				modificarUsuario();
				actualizarTabla();
			}
		});

		usuarioModificacionView.setBtnCancelar(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Cancelar Usuario");
                usuarioModificacionView.dispose();
                actualizarTabla();
			}
		});
	}
	
	private void modificarUsuario() {

		UsuarioDAOimpl usuarioDAO = new UsuarioDAOimpl();

		usuario.setNombre(usuarioModificacionView.getNombre());
		usuario.setApellido(usuarioModificacionView.getApellido());
		usuario.setEmail(usuarioModificacionView.getEmail());
		usuario.setTipoUsuario(usuarioModificacionView.getTipo().getSelectedItem().toString());
		usuario.setContrasena(usuarioModificacionView.getContrasena());

		if (usuarioDAO.modificarUsuario(usuario)) {
			System.out.println("Usuario modificado");
			JOptionPane.showMessageDialog(null, "Usuario modificado con exito!", "Alta Usuario", JOptionPane.INFORMATION_MESSAGE);
		} else {
			System.out.println("Error al modificar usuario.");
			JOptionPane.showMessageDialog(null, "Error al guardar usuario.", "Error", JOptionPane.ERROR_MESSAGE);
		}

		usuarioModificacionView.dispose();
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
