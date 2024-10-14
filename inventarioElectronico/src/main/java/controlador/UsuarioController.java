package controlador;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import modelo.Usuario;

public class UsuarioController {
    private Usuario usuario;
    private SessionFactory sessionFactory;

    // Constructor
    public UsuarioController(Usuario usuario) {
        this.usuario = usuario;
        Configuration configuration = new Configuration();
		configuration.configure("hibernate.cfg.xml");
		StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build();
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    }

    // Método para iniciar sesión
    public Usuario iniciarSesion(String user, String password) {
        //return usuario.validarCredenciales(email, password);
    	Session session = null;
    	try {
	    	session = sessionFactory.openSession();
	    	String hql = "FROM Usuario WHERE usuarioAd = :user AND contrasena = :password";
	    	Query query = session.createQuery(hql);
	    	query.setParameter("user", user);
	    	query.setParameter("password", password);
	    	@SuppressWarnings("rawtypes")
			List results = query.list();
	    	
			if (results.size() > 0) {
				return (Usuario) results.get(0);
			} else {
				return null;
			}
    	} finally {
    		if (session != null) {
    			session.close();
    		}
    	}
    	
    }

    // Método para registrar un nuevo usuario (ejemplo adicional)
    public void registrarUsuario(String usuarioAd, String contrasena, String nombre, String apellido, String email,
			String tipoUsuario, Boolean baja) {

    }

    // Método para actualizar la información del usuario (ejemplo adicional)
    public void actualizarPerfil(String usuarioAd, String contrasena, String nombre, String apellido, String email,
			String tipoUsuario, Boolean baja) {
    	
    }
    
    public String mostrarTipoUsuario() {
        return usuario.getTipoUsuario(); // Asegúrate de que 'getTipoUsuario()' esté implementado en la clase Usuario
    }

	public boolean tienePermiso(Usuario usuarioLogin, String categoria) {
		
		Map<String, List<String>> permisosPorUsuario = new HashMap<>();
		permisosPorUsuario.put("Administrador", Arrays.asList("Gestión Activos", "Gestión Usuarios", "Compras" , "Mantenimiento / Reparación", "Backup", "Reportes"));
		permisosPorUsuario.put("It", Arrays.asList("Gestión Activos", "Gestión Usuarios", "Mantenimiento / Reparación", "Backup", "Reportes"));
		permisosPorUsuario.put("Finanzas", Arrays.asList("Gestión Activos", "Reportes"));
		permisosPorUsuario.put("Compras", Arrays.asList("Compras"));
		
		List<String> permisos = permisosPorUsuario.get(usuarioLogin.getTipoUsuario());
		
		if (permisos == null) {
			return false;
        }
		
		return permisos.contains(categoria);
	}
}