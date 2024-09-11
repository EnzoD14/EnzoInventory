package view.events;

import view.screen.UsuarioGestionView;

public class UsuarioGestionEvents {
	
	private UsuarioGestionView usuarioGestionView;
	
	public UsuarioGestionEvents(UsuarioGestionView usuarioGestionView) {
		this.usuarioGestionView = usuarioGestionView;
		initEventHandlers();
	}
	
	private void initEventHandlers() {
		usuarioGestionView.setModificarButton(e -> {
			// TODO Auto-generated method stub
			System.out.println("Modificar Usuario");

		});
	}
	

}
