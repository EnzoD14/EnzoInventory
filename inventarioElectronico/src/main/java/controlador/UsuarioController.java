package controlador;

import modelo.Usuario;

public class UsuarioController {
    private Usuario usuario;

    // Constructor
    public UsuarioController(Usuario usuario) {
        this.usuario = usuario;
    }

    // Método para iniciar sesión
    public boolean iniciarSesion(String email, String password) {
        // Aquí se podría conectar a una base de datos para validar el usuario
        return usuario.validarCredenciales(email, password);
    }

    // Método para registrar un nuevo usuario (ejemplo adicional)
    public void registrarUsuario(String usuarioAd, String contrasena, String nombre, String apellido, String email,
			String tipoUsuario, Boolean baja) {
        //usuario = new Usuario(usuarioAd, contrasena, nombre, apellido, email, tipoUsuario, true);
        // Aquí se podría guardar el usuario en una base de datos
    }

    // Método para actualizar la información del usuario (ejemplo adicional)
    public void actualizarPerfil(String usuarioAd, String contrasena, String nombre, String apellido, String email,
			String tipoUsuario, Boolean baja) {
    	
        
        //Actualizar usuario en bdd
    }
    
    public String mostrarTipoUsuario() {
        return usuario.getTipoUsuario(); // Asegúrate de que 'getTipoUsuario()' esté implementado en la clase Usuario
    }
}