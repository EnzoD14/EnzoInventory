package view.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import modelo.Usuario;
import modelo.dao.impl.UsuarioDAOimpl;
import view.screen.UsuarioAltaView;
import view.screen.UsuarioGestionView;
import view.screen.UsuarioModificacionView;

public class UsuarioGestionEvents {
	
	private UsuarioGestionView usuarioGestionView;
	private Usuario tempUser;
	private UsuarioAltaView usuarioAltaView;
	private UsuarioAltaEvents usuarioAltaEvents;
	
	public UsuarioGestionEvents(UsuarioGestionView usuarioGestionView) {
		this.usuarioGestionView = usuarioGestionView;
		this.tempUser = new Usuario();
		initEventHandlers();
	}
	
	private void initEventHandlers() {
		UsuarioDAOimpl usuarioDAO = new UsuarioDAOimpl();
		
		usuarioGestionView.setModificarButton(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Modificar Usuario");
                tempUser = usuarioDAO.obtenerUsuarioPorUsuarioAd(usuarioGestionView.getUsuarioAd());
                System.out.println(tempUser.getContrasena());
                UsuarioModificacionView usuarioModificacionView = new UsuarioModificacionView(tempUser);
                new UsuarioModificacionEvents(usuarioModificacionView, usuarioGestionView ,tempUser);
             }
         });
		
		usuarioGestionView.setEliminarButton(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Eliminar Usuario");
                tempUser = usuarioDAO.obtenerUsuarioPorUsuarioAd(usuarioGestionView.getUsuarioAd());
                if (usuarioDAO.eliminarUsuario(tempUser)) {
                	JOptionPane.showMessageDialog(null, "Usuario eliminado con Exito", "Baja Usuario", JOptionPane.INFORMATION_MESSAGE);
                } else {
                	JOptionPane.showMessageDialog(null, "Error al eliminar Usuario", "Baja Usuario", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
		
		usuarioGestionView.setCrearButton(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Crear Usuario");
                UsuarioAltaView usuarioAltaView = new UsuarioAltaView();
                new UsuarioAltaEvents(usuarioAltaView, usuarioGestionView);
            }
        });
	}
	

}
