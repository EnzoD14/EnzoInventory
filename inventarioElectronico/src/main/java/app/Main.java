package app;

import controlador.UsuarioController;
import modelo.Usuario;
import view.screen.LoginView;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Instancia del controlador y modelo necesarios para la vista
       UsuarioController controlador = new UsuarioController(new Usuario("1", "1", "Enzo", "Dolera", "enzo", "It", 0));
       // UsuarioController controlador2 = new UsuarioController(new Usuario("2", "2", "Gonzalo", "Agostino", "gonzalo", "Finanzas", true));
        
        // Instancia de la vista que quieres probar
       LoginView loginView = new LoginView();
		
	}

}
